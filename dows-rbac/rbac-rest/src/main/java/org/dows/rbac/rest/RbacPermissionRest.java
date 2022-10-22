package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacPermission;
import org.dows.rbac.form.RbacPermissionForm;
import org.dows.rbac.service.RbacPermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-权限(RbacPermission)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@Api(tags = "rbac-权限")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacPermission")
public class RbacPermissionRest implements MybatisCrudRest<RbacPermissionForm, RbacPermission, RbacPermissionService> {

    //private final RbacPermissionBiz rbacPermissionBiz;

}

