package org.dows.rbac.rest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.framework.api.Response;
import org.dows.rbac.biz.RbacRoleBiz;
import org.dows.rbac.crud.entity.RbacRole;
import org.dows.rbac.crud.service.RbacRoleService;
import org.dows.rbac.pojo.query.RoleAccountQuery;
import org.dows.rbac.pojo.query.RolePermQuery;
import org.dows.rbac.pojo.vo.GroupVo;
import org.dows.rbac.pojo.vo.RbacAccreditVo;
import org.dows.rbac.pojo.vo.RoleVo;
import org.dows.rbac.pojo.vo.SimpleRoleVo;
import org.dows.rbac.rest.RbacRoleRest;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RBAC-角色(RbacRole)表控制层
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:59:09
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class RbacRoleController implements RbacRoleRest<RbacRole, RbacRoleService> {
    @Getter
    private final RbacRoleService service;
    private final RbacRoleBiz rbacRoleBiz;

    @Override
    public Response<IPage<RoleVo>> queryRoleList(RoleListQuery roleListQuery) {
        return Response.ok(rbacRoleBiz.queryRoleList(roleListQuery));
    }

    @Override
    public Response<List<SimpleRoleVo>> querySimpleRoleList(RoleListQueryO roleListQuery) {
        return Response.ok(rbacRoleBiz.querySimpleRoleList(roleListQuery));
    }

    @Override
    public Response<String> update(RoleUpdate roleUpdate) {
        return Response.ok(rbacRoleBiz.update(roleUpdate));
    }

    @Override
    public Response<String> deleteRole(IdsUpdate ids) {
        return Response.ok(rbacRoleBiz.deleteRole(ids.getIds()));
    }

    @Override
    public Response<IPage<AccountVo>> queryRoleAccount(RoleAccountQuery roleAccountQuery) {
        return Response.ok(rbacRoleBiz.queryRoleAccount(roleAccountQuery));
    }


    @Override
    public Response<String> permForRole(List<PermRoleUpdate> permRoleList) {
        return Response.ok(rbacRoleBiz.permForRole(permRoleList));
    }

    @Override
    public Response<List<RbacAccreditVo>> queryRoleperm(RolePermQuery rolepermQuery) {
        return Response.ok(rbacRoleBiz.queryRoleperm(rolepermQuery));
    }


    @Override
    public Response<IPage<GroupVo>> queryRoleGroup(RoleAccountQuery roleAccountQuery) {
        return Response.ok(rbacRoleBiz.queryRoleGroup(roleAccountQuery));
    }

    @Override
    public Response<String> copyRole(RoleCopy roleCopy) {
        return Response.ok(rbacRoleBiz.copyRole(roleCopy));
    }
}
