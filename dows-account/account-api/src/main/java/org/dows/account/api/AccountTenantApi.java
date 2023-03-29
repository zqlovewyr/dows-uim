package org.dows.account.api;

import org.dows.account.bo.AccountTenantBo;
import org.dows.account.vo.AccountTenantVo;
import org.dows.framework.api.Response;

/**
 * 账号-租户维度信息(AccountTenant)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:35
 */
public interface AccountTenantApi {

    /**
     * 更新账户租户关联信息表
     * @param accountTenantBo
     * @return
     */
    Response<Boolean> updateAccountTenant(AccountTenantBo accountTenantBo);

    /**
     * 查询账户租户关联信息表
     * @param accountTenantBo
     * @return
     */
    Response<AccountTenantVo> getAccountTenant(AccountTenantBo accountTenantBo);
}
