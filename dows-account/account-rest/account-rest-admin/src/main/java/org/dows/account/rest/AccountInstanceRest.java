package org.dows.account.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountInstanceBiz;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.entity.AccountInstance;
import org.dows.account.form.AccountInstanceForm;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final AccountInstanceBiz accountInstanceBiz;

    @ApiOperation("实例注册")
    @PostMapping("/register")
    public Response<AccountInstanceVo> register(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return Response.ok(accountInstanceBiz.createAccountInstance(accountInstanceDTO));
    }

    @PostMapping("/batchRegister")
    public void batchRegister(@RequestParam MultipartFile file,
                              @RequestParam String appId,
                              @RequestParam(required = false) Long rbacRoleId,
                              @RequestParam(required = false) String accountOrgOrgId,
                              @RequestParam(required = false) String password,
                              @RequestParam(required = false) String avatar,
                              @RequestParam(required = false) String source,
                              @RequestParam(required = false) String phone) {
        accountInstanceBiz.batchRegister(file, appId, rbacRoleId, accountOrgOrgId, password, avatar, source, phone);
    }
}

