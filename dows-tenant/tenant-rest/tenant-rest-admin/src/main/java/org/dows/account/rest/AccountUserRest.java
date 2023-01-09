package org.dows.user.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountUser;
import org.dows.account.form.AccountUserForm;
import org.dows.account.service.AccountUserService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
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

