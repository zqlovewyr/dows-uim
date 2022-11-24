package org.dows.account.biz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountIdentifier;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountRole;
import org.dows.account.service.AccountIdentifierService;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.exceptions.BaseException;
import org.dows.rbac.biz.EnumRbacStatusCode;
import org.dows.rbac.biz.RbacException;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


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

    /**
     * runsix method process
     * 1.check whether accountIdentifier queried by appId & identifier exist
     * 2.save accountIdentifier
     * 3.save accountInstance
     * 4.save accountRole if rbacRoleId exist
     * 5.convert entity to vo and return
    */
    @Transactional(rollbackFor = Exception.class)
    public AccountInstanceVo createAccountInstance(AccountInstanceDTO accountInstanceDTO) {
                AccountUtil.validateAIDTO(accountInstanceDTO);
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
                    .select()
                    .eq(RbacRole::getId, accountInstanceDTO.getRbacRoleId())
                    .oneOpt()
                    .orElseThrow(() -> {
                        throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
//                        throw new BaseException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
                    });
            accountRoleService.save(
                    /* runsix:TODO tenantId has not been used */
                    AccountRole.builder()
                            .roleId(rbacRole.getId().toString())
                            .roleName(rbacRole.getRoleName())
                            .roleCode(rbacRole.getRoleCode())
                            .principalType(EnumAccountRolePrincipalType.PERSONAL.getCode())
                            .principalId(accountInstance.getAccountId())
                            .principalName(accountInstance.getAccountName())
                            .build()
            );
        }
        AccountInstanceVo accountInstanceVo = new AccountInstanceVo();
        BeanUtils.copyProperties(accountInstance, accountInstanceVo);
        return accountInstanceVo;
    }
}
