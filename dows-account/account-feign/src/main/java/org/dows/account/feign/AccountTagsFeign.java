package org.dows.account.feign;

import org.dows.account.api.AccountTagsApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-标签维度信息(AccountTags)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:26
 */

@FeignClient(name = "cop-account", contextId = "accountTags", path = "/accountTags")
public interface AccountTagsFeign extends AccountTagsApi {


}
