package org.dows.account.feign;

import org.dows.account.api.AccountGroupApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-账号组维度信息(AccountGroup)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:23
 */

@FeignClient(name = "cop-account", contextId = "accountGroup", path = "/accountGroup")
public interface AccountGroupFeign extends AccountGroupApi {


}
