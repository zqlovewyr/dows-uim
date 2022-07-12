package org.dows.account.feign;

import org.dows.account.api.AccountOrganizationApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-组织架构维度信息(AccountOrganization)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:26
 */

@FeignClient(name = "cop-account", contextId = "accountOrganization", path = "/accountOrganization")
public interface AccountOrganizationFeign extends AccountOrganizationApi {


}
