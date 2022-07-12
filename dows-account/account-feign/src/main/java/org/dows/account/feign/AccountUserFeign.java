package org.dows.account.feign;

import org.dows.account.api.AccountUserApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-用户维度信息(AccountUser)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:27
 */

@FeignClient(name = "cop-account", contextId = "accountUser", path = "/accountUser")
public interface AccountUserFeign extends AccountUserApi {


}
