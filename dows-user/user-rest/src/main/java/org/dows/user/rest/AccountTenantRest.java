package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.AccountTenant;
import org.dows.user.form.AccountTenantForm;
import org.dows.user.service.AccountTenantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-租户(AccountTenant)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@Api(tags = "账号-租户")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountTenant")
public class AccountTenantRest implements MybatisCrudRest<AccountTenantForm, AccountTenant, AccountTenantService> {

    //private final AccountTenantBiz accountTenantBiz;

}

