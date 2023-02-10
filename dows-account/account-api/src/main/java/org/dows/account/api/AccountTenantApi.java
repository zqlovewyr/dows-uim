package org.dows.account.api;

import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountTenantDTO;
import org.dows.framework.api.Response;

/**
 * 账号-租户维度信息(AccountTenant)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:35
 */
public interface AccountTenantApi {
    /**
     * 插入 租户信息
     *
     * @param accountTenantDTO
     */
    Response<String> createAccountTenant(AccountTenantDTO accountTenantDTO);
}
