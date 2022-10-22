package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacLog;
import org.dows.rbac.form.RbacLogForm;
import org.dows.rbac.service.RbacLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-日志(RbacLog)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:16
 */
@Api(tags = "rbac-日志")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacLog")
public class RbacLogRest implements MybatisCrudRest<RbacLogForm, RbacLog, RbacLogService> {

    //private final RbacLogBiz rbacLogBiz;

}

