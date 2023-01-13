package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupInfoApi;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.form.AccountGroupInfoForm;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 账号-组(AccountGroupInfo)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@Api(tags = "账号-组-实例")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountGroupInfo")
public class AccountGroupInfoRest implements MybatisCrudRest<AccountGroupInfoForm, AccountGroupInfo, AccountGroupInfoService> {

    private final AccountGroupInfoApi accountGroupInfoApi;

    @ApiOperation("自定义查询 账号-组负责人 分页列表")
    @PostMapping("/customAccountGroupInfoList")
    public Response customAccountGroupInfoList(@RequestBody AccountGroupInfoDTO accountGroupInfoDTO) {
        Response<IPage<AccountGroupInfoVo>> voList = accountGroupInfoApi.customAccountGroupInfoList(accountGroupInfoDTO);
        return Response.ok(voList);
    }

    @ApiOperation("插入 账号-组-实例")
    @PostMapping("/insertAccountGroup")
    public Response insertAccountGroup(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        Response<Boolean> flag = accountGroupInfoApi.insertAccountGroup(accountOrgGroupDTO);
        return Response.ok(flag);
    }

    @ApiOperation("批量插入 账号-组-实例")
    @PostMapping("/batchInsertAccountGroup")
    public Response batchInsertAccountGroup(@RequestBody List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        Response<Boolean> flag = accountGroupInfoApi.batchInsertAccountGroup(accountOrgGroupDTOs);
        return Response.ok(flag);
    }

    @ApiOperation("更新 账号-组-实例")
    @PostMapping("/updateAccountGroup")
    public Response updateAccountGroup(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        Response<Boolean> flag = accountGroupInfoApi.updateAccountGroup(accountOrgGroupDTO);
        return Response.ok(flag);
    }

    @ApiOperation("删除单个 账号-组-实例")
    @PostMapping("/deleteAccountGroup")
    public Response deleteAccountGroup(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        Response<Boolean> flag = accountGroupInfoApi.deleteAccountGroup(accountOrgGroupDTO);
        return Response.ok(flag);
    }

    @ApiOperation("批量删除 账号-组-实例")
    @DeleteMapping("/batchDeleteGroups")
    public Response batchDeleteGroups(@RequestBody List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        Response<Boolean> flag = accountGroupInfoApi.batchDeleteGroups(accountOrgGroupDTOs);
        return Response.ok(flag);
    }
}

