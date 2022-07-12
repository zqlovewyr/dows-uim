package org.dows.account.feign;

import org.dows.account.api.AccountMembersApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-会员维度信息(AccountMembers)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:25
 */

@FeignClient(name = "cop-account", contextId = "accountMembers", path = "/accountMembers")
public interface AccountMembersFeign extends AccountMembersApi {


}
