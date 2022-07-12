package org.dows.account.feign;

import org.dows.account.api.AccountInstanceApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-实例维度信息(AccountInstance)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:24
 */

@FeignClient(name = "cop-account", contextId = "accountInstance", path = "/accountInstance")
public interface AccountInstanceFeign extends AccountInstanceApi {


}
