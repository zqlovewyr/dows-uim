package org.dows.account.feign;

import org.dows.account.api.PrincipalRoleApi;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 账号-主体角色维度信息(PrincipalRole)Feign接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-18 14:35:52
 */

@FeignClient(name = "cop-account", contextId = "principalRole", path = "/principalRole")
public interface PrincipalRoleFeign extends PrincipalRoleApi {


}
