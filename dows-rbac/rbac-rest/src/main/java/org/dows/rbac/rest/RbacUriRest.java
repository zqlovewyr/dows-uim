package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacUri;
import org.dows.rbac.form.RbacUriForm;
import org.dows.rbac.service.RbacUriService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-url(RbacUri)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:20
 */
@Api(tags = "rbac-url")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacUri")
public class RbacUriRest implements MybatisCrudRest<RbacUriForm, RbacUri, RbacUriService> {

    //private final RbacUriBiz rbacUriBiz;

}

