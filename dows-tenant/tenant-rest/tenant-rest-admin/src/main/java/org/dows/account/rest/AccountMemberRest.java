package org.dows.user.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountMember;
import org.dows.account.form.AccountMemberForm;
import org.dows.account.service.AccountMemberService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-会员(AccountMember)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:27
 */
@Api(tags = "账号-会员")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountMember")
public class AccountMemberRest implements MybatisCrudRest<AccountMemberForm, AccountMember, AccountMemberService> {

    //private final AccountMemberBiz accountMemberBiz;

}

