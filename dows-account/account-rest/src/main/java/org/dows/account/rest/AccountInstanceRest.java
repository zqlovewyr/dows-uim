package org.dows.account.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountInstanceBiz;
import org.dows.account.entity.AccountInstance;
import org.dows.account.form.AccountInstanceForm;
import org.dows.account.form.AccountUserResForm;
import org.dows.account.service.AccountInstanceService;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
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
@RequestMapping("accountInstanceInfo")
public class AccountInstanceRest implements MybatisCrudRest<AccountInstanceForm, AccountInstance, AccountInstanceService> {

    private final AccountInstanceBiz accountInstanceBiz;

    @PostMapping(value = "/save")
    @ApiOperation("保存用户")
    public Response saveUser(@RequestBody AccountUserResForm usersForm){
        try {
            //accountInstanceBiz.createAccountInstance();
            return Response.ok();

        } catch (Exception e) {
            return Response.fail(e.toString());
        }
    }

}

