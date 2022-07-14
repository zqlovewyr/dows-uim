package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.crud.service.AccountTenantService;
import org.springframework.stereotype.Service;

/**
 * 账号-租户维度信息(AccountTenant)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:45
 */
@RequiredArgsConstructor
@Service
public class AccountTenantBiz {
    private final AccountTenantService service;

}
