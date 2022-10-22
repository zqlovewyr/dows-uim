package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacRule;
import org.dows.rbac.form.RbacRuleForm;
import org.dows.rbac.service.RbacRuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-数据规则(RbacRule)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:19
 */
@Api(tags = "rbac-数据规则")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacRule")
public class RbacRuleRest implements MybatisCrudRest<RbacRuleForm, RbacRule, RbacRuleService> {

    //private final RbacRuleBiz rbacRuleBiz;

}

