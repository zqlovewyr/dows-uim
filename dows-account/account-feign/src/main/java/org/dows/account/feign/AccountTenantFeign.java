package org.dows.account.feign;

import org.dows.account.api.AccountTenantApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-租户维度信息(AccountTenant)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:27
 */

@FeignClient(name = "cop-account", contextId = "accountTenant", path = "/accountTenant")
public interface AccountTenantFeign extends AccountTenantApi {


}
