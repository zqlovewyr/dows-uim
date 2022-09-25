package org.dows.rbac.rest.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.rbac.biz.RbacPermissionBiz;
import org.dows.rbac.entity.RbacPermission;
import org.dows.rbac.form.PermTypeQuery;
import org.dows.rbac.form.PermUpdate;
import org.dows.rbac.query.PermCateQuery;
import org.dows.rbac.query.PermListQuery;
import org.dows.rbac.rest.RbacPermissionRest;
import org.dows.rbac.service.RbacPermissionService;
import org.dows.rbac.vo.PermSimpleVo;
import org.dows.rbac.vo.PermVo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RBAC权限资源(RbacPermission)表控制层
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:59:08
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class RbacPermissionController implements RbacPermissionRest<RbacPermission, RbacPermissionService> {
    @Getter
    private final RbacPermissionService service;
    private final RbacPermissionBiz rbacPermissionBiz;

    @Override
    public Response<IPage<PermVo>> queryPermList(PermListQuery permListQuery) {
        return Response.ok(rbacPermissionBiz.queryPermList(permListQuery));
    }

    public Response<List<PermSimpleVo>> queryPermSimpleList(PermTypeQuery permTypeQuery) {
        return Response.ok(rbacPermissionBiz.queryPermSimpleList(permTypeQuery));
    }

    public Response<List<PermSimpleVo>> queryAllPermList(PermCateQuery permCateQuery) {
        return Response.ok(rbacPermissionBiz.queryAllPermList(permCateQuery));
    }

    @Override
    public Response<String> update(PermUpdate permUpdate) {
        return Response.ok(rbacPermissionBiz.update(permUpdate));
    }

    @Override
    public Response<String> deletePerm(IdsUpdate ids) {
        return Response.ok(rbacPermissionBiz.deletePerm(ids.getIds()));
    }


    public Response<List<PermDTO>> queryCurrentPermList(@RequestHeader("account-info") String accountInfo) {
        return Response.ok(rbacPermissionBiz.queryCurrentPermList());
    }

    public Response<String> getHeader(@RequestHeader("account-info") String accountInfo) {
        return Response.ok(accountInfo);
    }
}
