package org.dows.account.biz;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountTenantApi;
import org.dows.account.biz.dto.AccountTenantDTO;
import org.dows.account.bo.AccountTenantBo;
import org.dows.account.entity.AccountTenant;
import org.dows.account.service.AccountTenantService;
import org.dows.framework.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountTenantBiz implements AccountTenantApi {

    @Autowired
    private AccountTenantService accountTenantService;

    @Override
    public Response<Boolean> updateAccountTenant(AccountTenantBo accountTenantBo){
        AccountTenantDTO accountTenantDTO = new AccountTenantDTO();
        BeanUtil.copyProperties(accountTenantBo,accountTenantDTO);
        boolean update = accountTenantService.lambdaUpdate()
                .set(AccountTenant::getUserId, accountTenantDTO.getUserId())
                .set(AccountTenant::getMerchantNo, accountTenantDTO.getMerchantNo())
                .eq(AccountTenant::getAccountId, accountTenantDTO.getAccountId())
                .update();
        return Response.ok(update);
    }

}
