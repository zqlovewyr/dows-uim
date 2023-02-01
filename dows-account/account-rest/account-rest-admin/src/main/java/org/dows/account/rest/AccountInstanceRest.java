package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountInstanceApi;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.entity.AccountInstance;
import org.dows.account.form.AccountInstanceForm;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final AccountInstanceApi accountInstanceApi;

/*    @ApiOperation("实例注册")
    @PostMapping("/register")
    public Response<AccountInstanceVo> register(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return Response.ok(accountInstanceBiz.createAccountInstance(accountInstanceDTO).getData());
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
    }*/

    @ApiOperation("查看 账号-实例-列表")
    @PostMapping("/customAccountInstanceList")
    public Response<IPage<AccountInstanceVo>> customAccountInstanceList(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return accountInstanceApi.customAccountInstanceList(accountInstanceDTO);
    }

    @ApiOperation("编辑 账号-实例")
    @PutMapping("/updateAccountInstanceById")
    public void updateAccountInstanceById(@RequestBody AccountInstanceDTO accountInstanceDTO) {
       accountInstanceApi.updateAccountInstanceById(accountInstanceDTO);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return accountInstanceApi.login(accountInstanceDTO);
    }
}

