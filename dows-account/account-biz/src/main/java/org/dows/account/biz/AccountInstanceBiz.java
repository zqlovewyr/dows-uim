package org.dows.account.biz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import static org.dows.account.biz.util.AccountUtil.getKeyOfkIdentifierAppIdV;


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
     * 2.check whether rbacRoleId exist
     * 3.check whether accountOrgOrgId exist
     * 4.save accountIdentifier
     * 5.save accountInstance
     * 6.save accountRole if rbacRoleId exist
     * 7.save accountGroup if orgId exist
     * 8.convert entity to vo and return
    */
    @Transactional(rollbackFor = Exception.class)
    public AccountInstanceVo createAccountInstance(AccountInstanceDTO accountInstanceDTO) {
        accountInstanceDTO = AccountUtil.validateAndTrimAccountInstanceDTO(accountInstanceDTO);
        /* runsix:1.check whether accountIdentifier queried by appId & identifier exist */
        accountIdentifierService.lambdaQuery()
                .select(AccountIdentifier::getId)
                .eq(AccountIdentifier::getAppId, accountInstanceDTO.getAppId())
                .eq(AccountIdentifier::getIdentifier, accountInstanceDTO.getIdentifier())
                .oneOpt()
                .ifPresent((a) -> {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_EXIST_EXCEPTION);
                });
        /* runsix:2.check whether rbacRoleId exist */
        RbacRole rbacRole = null;
        if (Objects.nonNull(accountInstanceDTO.getRbacRoleId())) {
            rbacRole = rbacRoleService.lambdaQuery()
                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
                    .eq(RbacRole::getId, accountInstanceDTO.getRbacRoleId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:3.check whether accountOrgOrgId exist */
        AccountOrg accountOrg = null;
        if (StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId())) {
            accountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .eq(AccountOrg::getOrgId, accountInstanceDTO.getAccountOrgOrgId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
                    });
        }
        /* runsix:4.save accountIdentifier */
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
        accountIdentifier.setAccountId(IdWorker.getIdStr());
        accountIdentifierService.save(accountIdentifier);
        /* runsix:5.save accountInstance */
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
        accountInstance.setAccountId(accountIdentifier.getAccountId());
        accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
        accountInstanceService.save(accountInstance);
        /* runsix:6.save accountRole if rbacRoleId exist */
        if (Objects.nonNull(rbacRole)) {
            accountRoleService.save(
                    AccountRole
                            .builder()
                            .roleId(accountInstanceDTO.getRbacRoleId().toString())
                            .roleName(rbacRole.getRoleName())
                            .roleCode(rbacRole.getRoleCode())
                            .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                            .principalId(accountInstance.getAccountId())
                            .principalName(accountInstanceDTO.getAccountName())
                            .build()
            );
        }
        /* runsix:7.save accountGroup if orgId exist */
        if (Objects.nonNull(accountOrg)) {
            accountGroupService.save(
                    AccountGroup
                            .builder()
                            .orgId(accountInstanceDTO.getAccountOrgOrgId())
                            .orgName(accountOrg.getOrgName())
                            .accountId(accountInstance.getAccountId())
                            .accountName(accountInstance.getAccountName())
                            .appId(accountInstanceDTO.getAppId())
                            .build()
            );
        }
        /* runsix:8.convert entity to vo and return */
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        accountInstanceVo.setAccountId(Long.parseLong(accountInstance.getAccountId()));
        return accountInstanceVo;
    }

    public List<AccountInstanceDTO> getAccountInstanceDTOListByFile(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<AccountInstanceDTO> accountInstanceDTOList = new ArrayList<>();
        EasyExcel.read(inputStream, AccountInstanceDTO.class, new ReadListener<AccountInstanceDTO>() {
            @Override
            public void invoke(AccountInstanceDTO accountInstanceDTO, AnalysisContext analysisContext) {
                if (AccountUtil.excelValidateAccountInstanceDTO(accountInstanceDTO)) {
                    accountInstanceDTO.setAppId(appId);
                    if (Objects.isNull(accountInstanceDTO.getRbacRoleId()) && Objects.nonNull(rbacRoleId)) {
                        accountInstanceDTO.setRbacRoleId(rbacRoleId);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getAccountOrgOrgId()) && StringUtils.isNotBlank(accountOrgOrgId)) {
                        accountInstanceDTO.setAccountOrgOrgId(accountOrgOrgId);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getPassword()) && StringUtils.isNotBlank(password)) {
                        accountInstanceDTO.setPassword(password);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getAvatar()) && StringUtils.isNotBlank(avatar)) {
                        accountInstanceDTO.setAvatar(avatar);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getSource()) && StringUtils.isNotBlank(source)) {
                        accountInstanceDTO.setSource(source);
                    }
                    if (StringUtils.isBlank(accountInstanceDTO.getPhone()) && StringUtils.isNotBlank(phone)) {
                        accountInstanceDTO.setPhone(phone);
                    }
                    accountInstanceDTOList.add(accountInstanceDTO);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet().doRead();
        return accountInstanceDTOList;
    }

    /**
     * runsix method process
     * 1.check whether input appId & identifier duplicated
     * 2.check whether accountIdentifier queried by appId & identifier exist
     * 3.check whether rbacRoleId exist
     * 4.check whether accountOrgOrgId exist
     * 5.batch save accountIdentifier
     * 6.batch save accountInstance
     * 7.batch save accountRole if rbacRoleId exist
     * 8.batch save accountGroup if orgId exist
    */
    @Transactional(rollbackFor = Exception.class)
    public void batchCreateAccountInstance(List<AccountInstanceDTO> accountInstanceDTOList) {
        /* runsix:TODO 所有in查询都要判空。注意 */
        accountInstanceDTOList.parallelStream().forEach(AccountUtil::validateAndTrimAccountInstanceDTO);
        /* runsix:1.check whether input appId & identifier duplicated */
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
            kIdentifierAppIdVAccountId.put(getKeyOfkIdentifierAppIdV(identifier,appId), IdWorker.getIdStr());
        });
        if (kIdentifierVAppIdSet.isEmpty()) {
            return;
        }
        /* runsix:2.check whether accountIdentifier queried by appId & identifier exist */
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
        /* runsix:3.check whether rbacRoleId exist */
        Set<Long> rbacRoleIdSet = accountInstanceDTOList.parallelStream()
                .map(AccountInstanceDTO::getRbacRoleId)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        Map<Long, RbacRole> kRbacRoleIdVRbacRole = new ConcurrentHashMap<>();
        if (!rbacRoleIdSet.isEmpty()) {
            kRbacRoleIdVRbacRole = rbacRoleService.lambdaQuery()
                    .select(RbacRole::getId, RbacRole::getRoleName, RbacRole::getRoleCode)
                    .in(RbacRole::getId, rbacRoleIdSet)
                    .list()
                    .parallelStream()
                    .collect(Collectors.toMap(RbacRole::getId, rbacRole -> rbacRole));
            if (kRbacRoleIdVRbacRole.size() != rbacRoleIdSet.size()) {
                throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
            }
        }
        /* runsix:4.check whether accountOrgOrgId exist */
        Set<String> accountOrgOrgIdSet = accountInstanceDTOList.parallelStream()
                .map(AccountInstanceDTO::getAccountOrgOrgId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Map<String, AccountOrg> kAccountOrgOrgIdVAccountOrg = new ConcurrentHashMap<>();
        if (!accountOrgOrgIdSet.isEmpty()) {
            kAccountOrgOrgIdVAccountOrg = accountOrgService.lambdaQuery()
                    .select(AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .in(AccountOrg::getOrgId, accountOrgOrgIdSet)
                    .list()
                    .parallelStream()
                    .collect(Collectors.toMap(AccountOrg::getOrgId, accountOrg -> accountOrg));
            if (kAccountOrgOrgIdVAccountOrg.size() != accountOrgOrgIdSet.size()) {
                throw new OrgException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
            }
        }
        /* runsix:5.batch save accountIdentifier */
        List<AccountIdentifier> accountIdentifierList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountIdentifier accountIdentifier = new AccountIdentifier();
            BeanUtils.copyProperties(accountInstanceDTO, accountIdentifier);
            accountIdentifier.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            return accountIdentifier;
        }).collect(Collectors.toList());
        accountIdentifierService.saveBatch(accountIdentifierList);
        /* runsix:6.batch save accountInstance */
        List<AccountInstance> accountInstanceList = accountInstanceDTOList.parallelStream().map(accountInstanceDTO -> {
            AccountInstance accountInstance = new AccountInstance();
            BeanUtils.copyProperties(accountInstanceDTO, accountInstance);
            accountInstance.setAccountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                    accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
            )));
            accountInstance.setPassword(new BCryptPasswordEncoder().encode(accountInstanceDTO.getPassword()));
            return accountInstance;
        }).collect(Collectors.toList());
        accountInstanceService.saveBatch(accountInstanceList);
        /* runsix:7.batch save accountRole if rbacRoleId exist */
        Map<Long, RbacRole> finalKRbacRoleIdVRbacRole = kRbacRoleIdVRbacRole;
        List<AccountRole> accountRoleList = accountInstanceDTOList.parallelStream()
                .filter(accountInstanceDTO ->  Objects.nonNull(accountInstanceDTO.getRbacRoleId()))
                .map(accountInstanceDTO -> AccountRole
                        .builder()
                        .roleId(accountInstanceDTO.getRbacRoleId().toString())
                        .roleName(finalKRbacRoleIdVRbacRole.get(accountInstanceDTO.getRbacRoleId()).getRoleName())
                        .roleCode(finalKRbacRoleIdVRbacRole.get(accountInstanceDTO.getRbacRoleId()).getRoleCode())
                        .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                        .principalId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                                accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
                        )))
                        .principalName(accountInstanceDTO.getAccountName())
                        .build()
                )
                .collect(Collectors.toList());
        accountRoleService.saveBatch(accountRoleList);
        /* runsix:8.batch save accountGroup if orgId exist */
        Map<String, AccountOrg> finalKAccountOrgOrgIdVAccountOrg = kAccountOrgOrgIdVAccountOrg;
        List<AccountGroup> accountGroupList = accountInstanceDTOList.parallelStream()
                .filter(accountInstanceDTO -> StringUtils.isNotBlank(accountInstanceDTO.getAccountOrgOrgId()))
                .map(accountInstanceDTO -> AccountGroup
                        .builder()
                        .orgId(accountInstanceDTO.getAccountOrgOrgId())
                        .orgName(finalKAccountOrgOrgIdVAccountOrg.get(accountInstanceDTO.getAccountOrgOrgId()).getOrgName())
                        .accountId(kIdentifierAppIdVAccountId.get(getKeyOfkIdentifierAppIdV(
                                accountInstanceDTO.getIdentifier(), accountInstanceDTO.getAppId()
                        )))
                        .accountName(accountInstanceDTO.getAccountName())
                        .appId(accountInstanceDTO.getAppId())
                        .build()
                )
                .collect(Collectors.toList());
        accountGroupService.saveBatch(accountGroupList);
    }

    public void batchRegister(MultipartFile file, String appId, Long rbacRoleId, String accountOrgOrgId, String password, String avatar, String source, String phone) {
        List<AccountInstanceDTO> accountInstanceDTOListByFile = getAccountInstanceDTOListByFile(
                file, appId, rbacRoleId, accountOrgOrgId, password, avatar, source, phone);
        batchCreateAccountInstance(accountInstanceDTOListByFile);
    }
}
