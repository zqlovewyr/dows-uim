package org.dows.account.biz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dows.account.biz.dto.AccountInstanceDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.exception.OrgException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.*;
import org.dows.account.service.*;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.rbac.biz.enums.EnumRbacStatusCode;
import org.dows.rbac.biz.exception.RbacException;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;


/**
 * @author runsix
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountInstanceBiz {
    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final RbacRoleService rbacRoleService;
    private final AccountRoleService accountRoleService;
    private final AccountOrgService accountOrgService;
    private final AccountGroupService accountGroupService;

    /**
     * runsix method process
     * 1.check whether accountIdentifier queried by appId & identifier exist
     * 2.save accountIdentifier
     * 3.save accountInstance
     * 4.save accountRole if rbacRoleId exist
     * 5.save accountGroup if orgId exist
     * 6.convert entity to vo and return
    */
    @Transactional(rollbackFor = Exception.class)
    public AccountInstanceVo createAccountInstance(AccountInstanceDTO accountInstanceDTO) {
        AccountUtil.validateAccountInstanceDTO(accountInstanceDTO);
        /* runsix:1 */
        accountIdentifierService.lambdaQuery()
                .select(AccountIdentifier::getId)
                .eq(AccountIdentifier::getAppId, accountInstanceDTO.getAppId())
                .eq(AccountIdentifier::getIdentifier, accountInstanceDTO.getIdentifier())
                .oneOpt()
                .ifPresent((a) -> {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION);
                });
        /* runsix:2 */
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
        accountIdentifier.setAccountId(IdWorker.getIdStr());
        accountIdentifierService.save(accountIdentifier);
        /* runsix:3 */
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
        accountInstance.setAccountId(accountIdentifier.getAccountId());
        accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
        accountInstanceService.save(accountInstance);
        /* runsix:4 */
        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
            RbacRole rbacRole = rbacRoleService.lambdaQuery()
                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
                    .eq(RbacRole::getId, accountInstanceDTO.getRbacRoleId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
                    });
            accountRoleService.save(
                    AccountRole
                            .builder()
                            .roleId(rbacRole.getId().toString())
                            .roleName(rbacRole.getRoleName())
                            .roleCode(rbacRole.getRoleCode())
                            .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                            .principalId(accountInstance.getAccountId())
                            .principalName(accountInstance.getAccountName())
                            .build()
            );
        }
        /* runsix:5 */
        if (StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId())) {
            AccountOrg accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .eq(AccountOrg::getOrgId, accountInstanceDTO.getAccountOrgOrgId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
            accountGroupService.save(
                    AccountGroup
                            .builder()
                            .orgId(accountOrg.getOrgId())
                            .orgName(accountOrg.getOrgName())
                            .accountId(accountInstance.getAccountId())
                            .accountName(accountInstance.getAccountName())
                            .appId(accountInstanceDTO.getAppId())
                            .build()
            );
        }
        /* runsix:6 */
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        return accountInstanceVo;
    }

    /**
     * runsix method process
     * 1.check whether input appId & identifier duplicated
     * 2.check whether accountIdentifier queried by appId & identifier exist in database
     * 3.batch save accountIdentifier
     * 4.batch save accountInstance
     * 5.batch save accountRole if rbacRoleId exist
     * 6.batch save accountGroup if orgId exist
    */
    @Transactional(rollbackFor = Exception.class)
    public void batchCreateAccountInstance(List<AccountInstanceDTO> accountInstanceDTOList) {
        /* runsix:1 */
        ConcurrentHashMap<String, Set<String>> kIdentifierVAppIdSet = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> kIdentifierAppIdVAccountId = new ConcurrentHashMap<>();
        accountInstanceDTOList.parallelStream().forEach(accountInstanceDTO -> {
            String identifier = accountInstanceDTO.getIdentifier();
            String appId = accountInstanceDTO.getAppId();
            Set<String> appIdSet = kIdentifierVAppIdSet.get(identifier);
            if (Objects.isNull(appIdSet)) {
                CopyOnWriteArraySet<String> newAppIdSet = new CopyOnWriteArraySet<>();
                newAppIdSet.add(appId);
                kIdentifierVAppIdSet.put(identifier, newAppIdSet);
            } else {
                boolean appIdIsNotExist = appIdSet.add(appId);
                if (!appIdIsNotExist) {
                    throw new AccountException(EnumAccountStatusCode.BATCH_IMPORT_IDENTIFIER_DUPLICATED);
                } else {
                    kIdentifierVAppIdSet.put(identifier, appIdSet);
                }
            }
            kIdentifierAppIdVAccountId.put(getKeyOfkIdentifierAppIdVAccountId(identifier,appId), IdWorker.getIdStr());
        });
        /* runsix:2 */
        accountIdentifierService.lambdaQuery()
                .select(AccountIdentifier::getIdentifier, AccountIdentifier::getAppId)
                .in(AccountIdentifier::getIdentifier, kIdentifierVAppIdSet.keySet())
                .list()
                .parallelStream()
                .forEach(accountIdentifier -> {
                    String identifier = accountIdentifier.getIdentifier();
                    String appId = accountIdentifier.getAppId();
                    if (kIdentifierVAppIdSet.containsKey(identifier) && kIdentifierVAppIdSet.get(identifier).contains(appId)) {
                        throw new AccountException(identifier + "," + EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION.getDescr());
                    }
                });
        /* runsix:3 */
        List<AccountIdentifier> accountIdentifierList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountIdentifier accountIdentifier = new AccountIdentifier();
            BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
            accountIdentifier.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdVAccountId(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            return accountIdentifier;
        }).collect(Collectors.toList());
        accountIdentifierService.saveBatch(accountIdentifierList);
        /* runsix:4 */
        List<AccountInstance> accountInstanceList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountInstance accountInstance = new AccountInstance();
            BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
            accountInstance.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdVAccountId(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
            return accountInstance;
        }).collect(Collectors.toList());
        accountInstanceService.saveBatch(accountInstanceList);
        /* runsix:5 */

        /* runsix:6 */
    }

    private String getKeyOfkIdentifierAppIdVAccountId(String identifier, String appId) {
        return identifier+"#"+appId;
    }
}
