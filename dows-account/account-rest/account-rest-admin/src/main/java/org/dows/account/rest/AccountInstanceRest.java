package org.dows.account.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountInstanceBiz;
import org.dows.account.biz.AccountInstanceDTO;
import org.dows.account.entity.AccountInstance;
import org.dows.account.form.AccountInstanceForm;
import org.dows.account.service.AccountInstanceService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-实例(AccountInstance)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:27
 */
@Api(tags = "账号-实例")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountInstance")
public class AccountInstanceRest implements MybatisCrudRest<AccountInstanceForm, AccountInstance, AccountInstanceService> {
//
//    private final AccountInstanceBiz accountInstanceBiz;
//    @PostMapping
//    public void register(@RequestBody AccountInstanceDTO accountInstanceDTO) {
//        accountInstanceBiz.createAccount(accountInstanceDTO);
//    }

    private final AccountInstanceBiz accountInstanceBiz;

    @PostMapping("/register")
    public void register(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        accountInstanceBiz.createAccountInstance(accountInstanceDTO);
    }
}

