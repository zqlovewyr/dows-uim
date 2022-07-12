package org.dows.account.feign;

import org.dows.account.api.AccountIdentifierApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-标识(AccountIdentifier)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:23
 */

@FeignClient(name = "cop-account", contextId = "accountIdentifier", path = "/accountIdentifier")
public interface AccountIdentifierFeign extends AccountIdentifierApi {


}
