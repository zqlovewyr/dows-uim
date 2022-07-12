package org.dows.account.feign;

import org.dows.account.api.AccountRelationshipsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-关系维度信息(AccountRelationships)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:26
 */

@FeignClient(name = "cop-account", contextId = "accountRelationships", path = "/accountRelationships")
public interface AccountRelationshipsFeign extends AccountRelationshipsApi {


}
