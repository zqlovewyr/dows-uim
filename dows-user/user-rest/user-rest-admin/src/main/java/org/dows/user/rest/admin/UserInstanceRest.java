package org.dows.user.rest.admin;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.UserInstance;
import org.dows.user.form.UserInstanceForm;
import org.dows.user.service.UserInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户-实例(UserInstance)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@Api(tags = "用户-实例")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userInstance")
public class UserInstanceRest implements MybatisCrudRest<UserInstanceForm, UserInstance, UserInstanceService> {

    //private final UserInstanceBiz userInstanceBiz;

}

