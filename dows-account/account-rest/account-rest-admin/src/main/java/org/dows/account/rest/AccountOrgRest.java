package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupInfoApi;
import org.dows.account.api.AccountInstanceApi;
import org.dows.account.api.AccountOrgApi;
import org.dows.account.api.AccountUserApi;
import org.dows.account.biz.AccountOrgBiz;
import org.dows.account.dto.*;
import org.dows.account.entity.AccountOrg;
import org.dows.account.form.AccountOrgForm;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    private final AccountGroupInfoApi accountGroupInfoApi;

    private final AccountOrgApi accountOrgApi;

    private final AccountInstanceApi accountInstanceApi;

    private final UserInstanceApi userInstanceApi;

    private final AccountUserApi accountUserApi;

    @ApiOperation("保存树型 账号-组织")
    @PostMapping({"/createTreeAccountOrg"})
    public Response createTreeAccountOrg(@RequestBody TreeAccountOrgDTO treeAccountOrgDto) {
        accountOrgApi.createTreeAccountOrg(treeAccountOrgDto);
        return Response.ok();
    }

    @ApiOperation("保存 账号-组织")
    @PostMapping({"/createAccountOrg"})
    public Response<Long> createAccountOrg(@RequestBody AccountOrgDTO accountOrgDTO) {
        return accountOrgApi.createAccountOrg(accountOrgDTO);
    }

    @ApiOperation("保存 机构-实例 同时创建机构和组及成员")
    @PostMapping("/insertAccountGroupInfo")
    @Transactional(rollbackFor = Exception.class)
    public void insertAccountGroupInfo(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        //1、创建机构
        AccountOrgDTO org = new AccountOrgDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO,org,new String[]{"id"});
        org.setDescr(accountOrgGroupDTO.getOrgDescr());
        Long orgId = accountOrgApi.createAccountOrg(org).getData();
        //2、创建一个默认管理员
        AccountInstanceDTO instance = new AccountInstanceDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO,instance,new String[]{"id"});
        instance.setPhone(accountOrgGroupDTO.getOwnerPhone());
        //2.1 默认创建的机构管理员
        instance.setRbacRoleId(1L);
        instance.setAccountOrgOrgId(orgId.toString());
        AccountInstanceVo vo = accountInstanceApi.createAccountInstance(instance).getData();
        //3、创建管理账户对应的用户信息
        UserInstanceDTO user = new UserInstanceDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO,instance,new String[]{"id"});
        Long userId = userInstanceApi.insertUserInstance(user).getData();
        //4、创建用户和账户映射关系
        AccountUserDTO accountUser = new AccountUserDTO();
        accountUser.setAccountId(vo.getId().toString());
        accountUser.setUserId(String.valueOf(userId));
        accountUserApi.createAccountUser(accountUser);
        //5、创建机构对应组
        AccountGroupInfoDTO info = new AccountGroupInfoDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO,info,new String[]{"id"});
        info.setDescr(accountOrgGroupDTO.getGroupDescr());
        info.setOrgId(orgId.toString());
        info.setAccountId(vo.getId().toString());
        info.setUserId(String.valueOf(userId));
        accountGroupInfoApi.insertAccountGroupInfo(info);
    }


    @ApiOperation("自定义查询 机构-实例 分页列表")
    @PostMapping("/customAccountGroupInfoList")
    public Response customAccountGroupInfoList(@RequestBody AccountGroupInfoDTO accountGroupInfoDTO) {
        Response<IPage<AccountGroupInfoVo>> voList = accountGroupInfoApi.customAccountGroupInfoList(accountGroupInfoDTO);
        return Response.ok(voList);
    }

}

