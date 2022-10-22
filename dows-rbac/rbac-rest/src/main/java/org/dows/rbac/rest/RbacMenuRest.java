package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacMenu;
import org.dows.rbac.form.RbacMenuForm;
import org.dows.rbac.service.RbacMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-菜单(RbacMenu)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:17
 */
@Api(tags = "rbac-菜单")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacMenu")
public class RbacMenuRest implements MybatisCrudRest<RbacMenuForm, RbacMenu, RbacMenuService> {

    //private final RbacMenuBiz rbacMenuBiz;

}

