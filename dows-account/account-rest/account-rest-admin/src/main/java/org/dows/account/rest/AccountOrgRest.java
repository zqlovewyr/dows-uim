package org.dows.account.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountOrg;
import org.dows.account.form.AccountOrgForm;
import org.dows.account.service.AccountOrgService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-组织架构(AccountOrg)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@Api(tags = "账号-组织架构")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountOrg")
public class AccountOrgRest implements MybatisCrudRest<AccountOrgForm, AccountOrg, AccountOrgService> {

    //private final AccountOrgBiz accountOrgBiz;

}

