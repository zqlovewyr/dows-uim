package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountTenantApi;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.dto.AccountTenantDTO;
import org.dows.account.entity.AccountTenant;
import org.dows.account.service.AccountTenantService;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2023/2/9 15:24
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountTenantBiz implements AccountTenantApi {
    private AccountTenantService accountTenantService;

    @Override
    public Response<String> createAccountTenant(AccountTenantDTO accountTenantDTO) {
        AccountTenant accountTenant = new AccountTenant();
        BeanUtils.copyProperties(accountTenantDTO,accountTenant);
        boolean flag = accountTenantService.save(accountTenant);
        if(flag == false){
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_TENANT_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(accountTenant.getId().toString());
    }
}
