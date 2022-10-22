package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.AccountRole;
import org.dows.user.form.AccountRoleForm;
import org.dows.user.service.AccountRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-角色(AccountRole)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Api(tags = "账号-角色")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountRole")
public class AccountRoleRest implements MybatisCrudRest<AccountRoleForm, AccountRole, AccountRoleService> {

    //private final AccountRoleBiz accountRoleBiz;

}

