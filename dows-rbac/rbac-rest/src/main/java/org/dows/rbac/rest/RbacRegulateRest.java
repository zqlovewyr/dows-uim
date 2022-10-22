package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacRegulate;
import org.dows.rbac.form.RbacRegulateForm;
import org.dows.rbac.service.RbacRegulateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-数据约束(RbacRegulate)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@Api(tags = "rbac-数据约束")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacRegulate")
public class RbacRegulateRest implements MybatisCrudRest<RbacRegulateForm, RbacRegulate, RbacRegulateService> {

    //private final RbacRegulateBiz rbacRegulateBiz;

}

