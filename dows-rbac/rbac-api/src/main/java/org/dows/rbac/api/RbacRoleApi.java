package org.dows.rbac.api;

import org.dows.framework.api.Response;
import org.dows.rbac.api.vo.RbacRoleVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * RBAC-角色(RbacRole)Api接口
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
public interface RbacRoleApi {
    @GetMapping("/v1/rbac-role/{id}")
    Response<RbacRoleVO> getById(@PathVariable String id);

    @GetMapping("/v1/rbac-roles")
    Response<List<RbacRoleVO>> getByIdList(@RequestBody List<String> rbacRoleIdList);

    @GetMapping("/v1/rbac-roles/{appid}")
    Response<List<RbacRoleVO>> getByIdListAndAppId(@RequestBody List<String> rbacRoleIdList,@PathVariable String appid);

}
