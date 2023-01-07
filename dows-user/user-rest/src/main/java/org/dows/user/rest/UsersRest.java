package org.dows.user.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.biz.UsersBiz;
import org.dows.user.entity.Users;
import org.dows.user.form.UsersForm;
import org.dows.user.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户(Users)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "用户基础信息")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("usersInfo")
public class UsersRest implements MybatisCrudRest<UsersForm, Users, UsersService> {

    private final UsersBiz usersBiz;

//    @PostMapping(value = "/save")
//    @ApiOperation("保存用户")
//    public Response saveUser(@RequestBody UsersForm usersForm){
//        try {
//            usersBiz.saveUser(usersForm);
//            return Response.ok();
//
//        } catch (Exception e) {
//            return Response.fail(e.toString());
//        }
//    }

}

