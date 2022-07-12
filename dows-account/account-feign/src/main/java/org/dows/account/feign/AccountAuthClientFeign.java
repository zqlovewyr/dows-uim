package org.dows.account.feign;

import org.dows.account.api.AccountAuthClientApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-授权客户端(AccountAuthClient)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:22
 */

@FeignClient(name = "cop-account", contextId = "accountAuthClient", path = "/accountAuthClient")
public interface AccountAuthClientFeign extends AccountAuthClientApi {


}
