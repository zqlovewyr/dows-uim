package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupInfoApi;
import org.dows.account.api.AccountInstanceApi;
import org.dows.account.api.AccountOrgApi;
import org.dows.account.api.AccountUserApi;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
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
import java.util.List;

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
    @PostMapping("/insertAccountOrg")
    @Transactional(rollbackFor = Exception.class)
    public void insertAccountOrg(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        //1、创建机构
        AccountOrgDTO org = new AccountOrgDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, org, new String[]{"id"});
        if(StringUtils.isNotEmpty(accountOrgGroupDTO.getOrgDescr())){
            org.setDescr(accountOrgGroupDTO.getOrgDescr());
        }
        Long orgId = accountOrgApi.createAccountOrg(org).getData();
        //2、创建一个默认管理员
        AccountInstanceDTO instance = new AccountInstanceDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, instance, new String[]{"id"});
        //2.1 默认创建的机构管理员
        instance.setRbacRoleId(1L);
        instance.setAccountOrgOrgId(orgId.toString());
        instance.setPrincipalType(EnumAccountRolePrincipalType.PERSONAL.getCode());
        instance.setAccountName(accountOrgGroupDTO.getOrgCode() + "888");
        AccountInstanceVo vo = accountInstanceApi.createAccountInstance(instance).getData();
        //3、创建管理账户对应的用户信息
        UserInstanceDTO user = new UserInstanceDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, instance, new String[]{"id"});
        String userId = userInstanceApi.insertUserInstance(user).getData();
        //4、创建用户和账户映射关系
        AccountUserDTO accountUser = new AccountUserDTO();
        accountUser.setAccountId(vo.getId().toString());
        accountUser.setUserId(String.valueOf(userId));
        accountUser.setAppId(accountOrgGroupDTO.getAppId());
        accountUser.setTentantId(accountOrgGroupDTO.getTenantId());
        accountUserApi.createAccountUser(accountUser);
        //5、创建机构对应组
        AccountGroupInfoDTO info = new AccountGroupInfoDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, info, new String[]{"id"});
        info.setDescr(accountOrgGroupDTO.getGroupDescr());
        info.setOrgId(orgId.toString());
        info.setAccountId(vo.getId().toString());
        info.setUserId(String.valueOf(userId));
        accountGroupInfoApi.insertAccountGroupInfo(info);
    }


    @ApiOperation("保存 团队-实例（不建立管理员）")
    @PostMapping("/insertAccountOrgNotAdmin")
    @Transactional(rollbackFor = Exception.class)
    public void insertAccountOrgNotAdmin(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        //1、创建机构
        AccountOrgDTO org = new AccountOrgDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, org, new String[]{"id"});
        if(StringUtils.isNotEmpty(accountOrgGroupDTO.getOrgDescr())){
            org.setDescr(accountOrgGroupDTO.getOrgDescr());
        }
        Long orgId = accountOrgApi.createAccountOrg(org).getData();
        //2、创建机构对应组
        AccountGroupInfoDTO info = new AccountGroupInfoDTO();
        BeanUtils.copyProperties(accountOrgGroupDTO, info, new String[]{"id"});
        info.setDescr(accountOrgGroupDTO.getGroupDescr());
        info.setOrgId(orgId.toString());
        accountGroupInfoApi.insertAccountGroupInfo(info);
    }


    @ApiOperation("自定义查询 机构-实例 分页列表")
    @PostMapping("/customAccountOrgList")
    public Response customAccountOrgList(@RequestBody AccountOrgDTO accountOrgDTO) {
        Response<IPage<AccountOrgVo>> voList = accountOrgApi.customAccountOrgList(accountOrgDTO);
        return Response.ok(voList);
    }


    @ApiOperation("更新 机构-实例")
    @PutMapping("/updateAccountOrg")
    @Transactional(rollbackFor = Exception.class)
    public void updateAccountGroupInfo(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        //1、更新组织架构表
        AccountOrgDTO accountOrg = new AccountOrgDTO();
        //1.1、设置组织架构属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
        accountOrg.setId(accountOrgGroupDTO.getId().toString());
        if (StringUtils.isNotEmpty(accountOrgGroupDTO.getOrgDescr())) {
            accountOrg.setDescr(accountOrgGroupDTO.getOrgDescr());
        }
        accountOrgApi.updateAccountOrgById(accountOrg);
        //2、更新组-实例表
        AccountGroupInfoVo data = accountGroupInfoApi.getAccountGroupInfoByOrgId(accountOrg.getId()).getData();
        //2.1、设置组实例属性
        BeanUtils.copyProperties(accountOrgGroupDTO, data, new String[]{"id"});
        AccountGroupInfoDTO dto = new AccountGroupInfoDTO();
        BeanUtils.copyProperties(data, dto);
        dto.setOrgId(accountOrg.getId().toString());
        accountGroupInfoApi.updateAccountGroupInfo(dto);
    }

    @ApiOperation("查看 机构-实例")
    @GetMapping("/getAccountOrgById/{id}")
    public Response<AccountGroupInfoVo> getAccountOrgById(@PathVariable("id") String id) {
        //1、根据ID获取组织机构信息
        AccountOrgVo vo = accountOrgApi.getAccountOrgById(id).getData();
        //2、根据组织机构ID获取组织信息
        return accountGroupInfoApi.getAccountGroupInfoByOrgId(vo.getId());
    }

    @ApiOperation("删除单个 机构-实例")
    @DeleteMapping("/deleteAccountOrgById/{id}")
    public void deleteAccountOrgById(@PathVariable("id") String id) {
        accountOrgApi.deleteAccountOrgById(id);
    }

    @ApiOperation("批量删除 机构-实例")
    @DeleteMapping("/batchDeleteAccountOrgs")
    public void batchDeleteAccountOrgs(@RequestParam("ids") List<String> ids) {
        accountOrgApi.batchDeleteAccountOrgs(ids);
    }
}

