package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.AccountUser;
import org.dows.user.form.AccountUserForm;
import org.dows.user.service.AccountUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-用户(实名认证后)(AccountUser)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@Api(tags = "账号-用户(实名认证后)")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountUser")
public class AccountUserRest implements MybatisCrudRest<AccountUserForm, AccountUser, AccountUserService> {

    //private final AccountUserBiz accountUserBiz;

}

