package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.*;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.util.DateUtil;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public Response<Integer> getHMECountList(@RequestParam String accountId) {
        Integer count = 0;
        //1、根据登录账户的accountId获取用户对应机构,0-超管，1-机构管理员
        AccountRoleVo accountRoleVo = accountRoleApi.getAccountRoleByPrincipalId(accountId).getData();
        //2、超级管理员
        if (accountRoleVo.getRoleId().equals("0")) {
            AccountInstanceDTO dto = new AccountInstanceDTO();
            List<AccountInstanceVo> voList = accountInstanceApi.getAccountInstanceList(dto).getData();
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
        if (accountRoleVo.getRoleId().equals("1")) {
            //2.1、获取登录账户的机构
            List<AccountGroupVo> list = accountGroupApi.getAccountGroupList(new AccountGroupDTO().builder().accountId(accountId).build()).getData();
            if (list != null && list.size() > 0) {
                String parentId = list.get(0).getId().toString();
                AccountOrgVo sonVo = accountOrgApi.getAccountOrgByPId(parentId).getData();
                AccountGroupDTO accountGroupDTO = new AccountGroupDTO();
                accountGroupDTO.setOrgId(sonVo.getId());
                List<AccountGroupVo> entityList = accountGroupApi.getAccountGroupList(accountGroupDTO).getData();
                if (entityList != null && entityList.size() > 0) {
                    for (AccountGroupVo entity : entityList) {
                        AccountRoleVo roleVo = accountRoleApi.getAccountRoleByPrincipalId(entity.getAccountId().toString()).getData();
                        if (roleVo.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                            count++;
                        }
                    }
                }
            }
        }
        return Response.ok(count);
    }

/*    @ApiOperation("查询 健管师（周环比）")
    @PostMapping("/getHMECountCRList")
    public Response<Integer> getHMECountCRList(@RequestParam String accountId) {
        Integer count = 0;
        //1、根据登录账户的accountId获取用户对应机构,0-超管，1-机构管理员
        AccountRoleVo accountRoleVo = accountRoleApi.getAccountRoleByPrincipalId(accountId).getData();
        //2、超级管理员
        if (accountRoleVo.getRoleId().equals("0")) {
            AccountInstanceDTO dto = new AccountInstanceDTO();
            dto.setStartTime();
            List<AccountInstanceVo> voList = accountInstanceApi.getAccountInstanceList(dto).getData();
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
        if (accountRoleVo.getRoleId().equals("1")) {
            //2.1、获取登录账户的机构
            List<AccountGroupVo> list = accountGroupApi.getAccountGroupList(new AccountGroupDTO().builder().accountId(accountId).build()).getData();
            if (list != null && list.size() > 0) {
                String parentId = list.get(0).getId().toString();
                AccountOrgVo sonVo = accountOrgApi.getAccountOrgByPId(parentId).getData();
                AccountGroupDTO accountGroupDTO = new AccountGroupDTO();
                accountGroupDTO.setOrgId(sonVo.getId());
                List<AccountGroupVo> entityList = accountGroupApi.getAccountGroupList(accountGroupDTO).getData();
                if (entityList != null && entityList.size() > 0) {
                    for (AccountGroupVo entity : entityList) {
                        AccountRoleVo roleVo = accountRoleApi.getAccountRoleByPrincipalId(entity.getAccountId().toString()).getData();
                        if (roleVo.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                            count++;
                        }
                    }
                }
            }
        }
        return Response.ok(count);
    }*/

    @ApiOperation("查询 健管师（日环比）")
    @PostMapping("/getHMECountYoYList")
    public Response<Double> getHMECountYoYList(@RequestParam String accountId) {
        Integer oldCount = 0;
        Integer newCount = 0;
        //1、根据登录账户的accountId获取用户对应机构,0-超管，1-机构管理员
        AccountRoleVo accountRoleVo = accountRoleApi.getAccountRoleByPrincipalId(accountId).getData();
        //2、超级管理员
        if (accountRoleVo.getRoleId().equals("0")) {
            AccountInstanceDTO oldDTO = new AccountInstanceDTO();
            //2.1、获取昨天
            Date oldDay = DateUtil.getYesterday();
            String oldStartTimeStr = DateUtil.formatDateToString(oldDay) + " 00:00:00";
            String oldEndTimeStr = DateUtil.formatDateToString(oldDay) + " 23:59:59";
            oldDTO.setStartTime(DateUtil.formatStringToDate(oldStartTimeStr));
            oldDTO.setEndTime(DateUtil.formatStringToDate(oldEndTimeStr));
            List<AccountInstanceVo> oldList = accountInstanceApi.getAccountInstanceList(oldDTO).getData();
            if (oldList != null && oldList.size() > 0) {
                for (AccountInstanceVo old : oldList) {
                    AccountRoleVo entity = accountRoleApi.getAccountRoleByPrincipalId(old.getId().toString()).getData();
                    if (entity.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                        oldCount++;
                    }
                }
            }
            //2.2、获取今天
            AccountInstanceDTO newDTO = new AccountInstanceDTO();
            String newStartTimeStr = DateUtil.formatDateToString(new Date()) + " 00:00:00";
            newDTO.setStartTime(DateUtil.formatStringToDate(newStartTimeStr));
            newDTO.setEndTime(new Date());
            List<AccountInstanceVo> newList = accountInstanceApi.getAccountInstanceList(newDTO).getData();
            if (newList != null && newList.size() > 0) {
                for (AccountInstanceVo new1 : newList) {
                    AccountRoleVo entity = accountRoleApi.getAccountRoleByPrincipalId(new1.getId().toString()).getData();
                    if (entity.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                        newCount++;
                    }
                }
            }

        }
        //2、机构管理员
        if (accountRoleVo.getRoleId().equals("1")) {
            //2.1、获取登录账户的机构
            List<AccountGroupVo> list = accountGroupApi.getAccountGroupList(new AccountGroupDTO().builder().accountId(accountId).build()).getData();
            if (list != null && list.size() > 0) {
                String parentId = list.get(0).getId().toString();
                AccountOrgVo sonVo = accountOrgApi.getAccountOrgByPId(parentId).getData();
                //2.1、获取昨天
                AccountGroupDTO oldAccountGroupDTO = new AccountGroupDTO();
                oldAccountGroupDTO.setOrgId(sonVo.getId());
                Date oldDay = DateUtil.getYesterday();
                String oldStartTimeStr = DateUtil.formatDateToString(oldDay) + " 00:00:00";
                String oldEndTimeStr = DateUtil.formatDateToString(oldDay) + " 23:59:59";
                oldAccountGroupDTO.setStartTime(DateUtil.formatStringToDate(oldStartTimeStr));
                oldAccountGroupDTO.setEndTime(DateUtil.formatStringToDate(oldEndTimeStr));
                List<AccountGroupVo> oldEntityList = accountGroupApi.getAccountGroupList(oldAccountGroupDTO).getData();
                if (oldEntityList != null && oldEntityList.size() > 0) {
                    for (AccountGroupVo entity : oldEntityList) {
                        AccountRoleVo roleVo = accountRoleApi.getAccountRoleByPrincipalId(entity.getAccountId().toString()).getData();
                        if (roleVo.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                            oldCount++;
                        }
                    }
                }
                //2、2 获取今天
                AccountGroupDTO newAccountGroupDTO = new AccountGroupDTO();
                String newStartTimeStr = DateUtil.formatDateToString(new Date()) + " 00:00:00";
                newAccountGroupDTO.setStartTime(DateUtil.formatStringToDate(newStartTimeStr));
                newAccountGroupDTO.setEndTime(new Date());
                List<AccountGroupVo> newEntityList = accountGroupApi.getAccountGroupList(newAccountGroupDTO).getData();
                if (newEntityList != null && newEntityList.size() > 0) {
                    for (AccountGroupVo entity : newEntityList) {
                        AccountRoleVo roleVo = accountRoleApi.getAccountRoleByPrincipalId(entity.getAccountId().toString()).getData();
                        if (roleVo.getRoleName() == BaseConstant.HEALTH_MANAGEMENT_ENGINEER) {
                            newCount++;
                        }
                    }
                }
            }
        }
        double res = new BigDecimal((double) (newCount - oldCount) / oldCount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return Response.ok(res);
    }
}

