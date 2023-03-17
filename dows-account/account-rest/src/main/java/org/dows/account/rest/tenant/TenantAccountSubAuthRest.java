package org.dows.account.rest.tenant;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountSubAuth;
import org.dows.account.form.AccountSubAuthForm;
import org.dows.account.service.AccountSubAuthService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 子账号权限(AccountSubAuth)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "子账号权限")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("tenant/accountsubauth")
public class TenantAccountSubAuthRest implements MybatisCrudRest<AccountSubAuthForm, AccountSubAuth, AccountSubAuthService> {


}

