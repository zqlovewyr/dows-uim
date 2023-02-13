package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.*;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.dto.AccountUserDTO;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountRole;
import org.dows.account.form.AccountInstanceForm;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.account.vo.AccountRoleVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.simpleframework.xml.Path;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    private final UserInstanceApi userInstanceApi;

    private final AccountUserApi accountUserApi;

    private final AccountRoleApi accountRoleApi;

    private final AccountGroupApi accountGroupApi;

    private final AccountOrgApi accountOrgApi;

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

    @ApiOperation("查看 账号-实例")
    @GetMapping("/getAccountInstanceById/{id}")
    public Response<AccountInstanceVo> getAccountInstanceById(@PathVariable("id") String id) {
        return accountInstanceApi.getAccountInstanceById(id);
    }

    @ApiOperation("删除 账号-实例")
    @PutMapping("/deleteAccountInstanceById/{id}")
    public Response<Boolean> deleteAccountInstanceById(@PathVariable("id") Long id) {
        return accountInstanceApi.deleteAccountInstanceById(id);
    }

    @ApiOperation("批量删除 账号-实例")
    @PutMapping("/batchDeleteAccountInstances")
    public void batchDeleteAccountInstances(@RequestParam("ids") List<String> ids) {
        accountInstanceApi.batchDeleteAccountInstances(ids);
    }

    @ApiOperation("新增 账号-实例")
    @PostMapping("/createAccountInstance")
    public Response<String> createAccountInstance(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        //1、新增账号信息
        AccountInstanceVo vo = accountInstanceApi.createAccountInstance(accountInstanceDTO).getData();
        //2、新增用户信息
        UserInstanceDTO user = new UserInstanceDTO();
        BeanUtils.copyProperties(accountInstanceDTO, user);
        user.setName(accountInstanceDTO.getUserName());
        String userId = userInstanceApi.insertUserInstance(user).getData();
        //3、创建账户和用户之间的关联关系
        AccountUserDTO accountUserDTO = new AccountUserDTO();
        accountUserDTO.setAccountId(vo.getId().toString());
        accountUserDTO.setUserId(userId);
        Long uionId = this.accountUserApi.createAccountUser(accountUserDTO).getData();
        return Response.ok(uionId.toString());
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return accountInstanceApi.login(accountInstanceDTO);
    }

    @ApiOperation("忘记密码")
    @PutMapping("/resetPwd")
    public Response<Boolean> resetPwd(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return accountInstanceApi.resetPwd(accountInstanceDTO);
    }

    @ApiOperation("查询 账户-实例-列表(不带分页)")
    @PostMapping("/getAccountInstanceList")
    public Response<List<AccountInstanceVo>> getAccountInstanceList(@RequestBody AccountInstanceDTO accountInstanceDTO) {
        return accountInstanceApi.getAccountInstanceList(accountInstanceDTO);
    }

    @ApiOperation("查询 健管师(数量)")
    @PostMapping("/getHMECountList")
    public Response<Integer> getHMECountList(@RequestBody AccountInstanceDTO accountInstaceDTO, @RequestParam("用户类别") String userType) {
        Integer count = 0;
        //1、根据登录账户的accountId获取用户对应机构,0-超管，1-机构管理员
        if (userType.equals("0")) {
            List<AccountInstanceVo> voList = accountInstanceApi.getAccountInstanceList(accountInstaceDTO).getData();
            if (voList != null && voList.size() > 0) {
                for (AccountInstanceVo vo : voList) {
                    AccountRoleVo entity = accountRoleApi.getAccountRoleByPrincipalId(vo.getId().toString()).getData();
                    if (entity.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                        count++;
                    }
                }
            }
        }
        //2、机构管理员
        if (userType.equals("1")) {
            //2.1、获取登录账户的机构
            List<AccountGroupVo> list = accountGroupApi.getAccountGroupList(new AccountGroupDTO().builder().accountId(accountInstaceDTO.getAccountId()).build()).getData();
            if (list != null && list.size() > 0) {
                String parentId = list.get(0).getId().toString();
                AccountOrgVo sonVo = accountOrgApi.getAccountOrgByPId(parentId).getData();
                AccountGroupDTO accountGroupDTO = new AccountGroupDTO();
                accountGroupDTO.setOrgId(sonVo.getId());
                List<AccountGroupVo> entityList = accountGroupApi.getAccountGroupList(accountGroupDTO).getData();
                if (entityList != null && entityList.size() > 0) {
                    for (AccountGroupVo entity : entityList) {
                        AccountRoleVo accountRoleVo = accountRoleApi.getAccountRoleByPrincipalId(entity.getAccountId().toString()).getData();
                        if (accountRoleVo.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                            count++;
                        }
                    }
                }
            }
        }
        return Response.ok(count);
    }
}

