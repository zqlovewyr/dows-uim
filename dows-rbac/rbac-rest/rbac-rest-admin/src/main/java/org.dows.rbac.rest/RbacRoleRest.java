package org.dows.rbac.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.api.vo.RbacRoleVO;
import org.dows.rbac.biz.RbacRoleBiz;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.form.RbacRoleForm;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author runsix
 */
@Api(tags = "权限-角色")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacRole")
public class RbacRoleRest implements RbacRoleApi, MybatisCrudRest<RbacRoleForm, RbacRole, RbacRoleService> {
    private final RbacRoleBiz rbacRoleBiz;
    @Override
    @GetMapping("/v1/rbac-role/{id}")
    public Response<RbacRoleVO> getById(@PathVariable String id) {
        return Response.ok(rbacRoleBiz.getById(id));
    }

    @Override
    @GetMapping("/v1/rbac-role")
    public Response<List<RbacRoleVO>> getByIdList( List<String> rbacRoleIdList) {
        return Response.ok(rbacRoleBiz.getByIdList(rbacRoleIdList));
    }

    @Override
    @GetMapping("/v1/rbac-roles/{appid}")
    public Response<List<RbacRoleVO>> getByIdListAndAppId(List<String> rbacRoleIdList, String appid) {
        return Response.ok(rbacRoleBiz.getByIdList(rbacRoleIdList,appid));
    }

}
