package org.dows.account.feign;

import org.dows.account.api.AccountOauthTokenApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-第三方账号授权(AccountOauthToken)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:25
 */

@FeignClient(name = "cop-account", contextId = "accountOauthToken", path = "/accountOauthToken")
public interface AccountOauthTokenFeign extends AccountOauthTokenApi {


}
