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

    @ApiOperation("保存 账号-组-实例")
    @PostMapping("/insertAccountGroupInfo")
    public Response<Long> insertAccountGroupInfo(@RequestBody AccountGroupInfoDTO accountGroupInfoDTO) {
        return accountGroupInfoApi.insertAccountGroupInfo(accountGroupInfoDTO);
    }

    @ApiOperation("批量保存 账号-组-实例")
    @PostMapping("/batchInsertAccountGroupInfo")
    public Response batchInsertAccountGroup(@RequestBody List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        Response<Boolean> flag = accountGroupInfoApi.batchInsertAccountGroupInfo(accountOrgGroupDTOs);
        return Response.ok(flag);
    }


    @ApiOperation("查看 账号-组-实例")
    @GetMapping("/getAccountGroupInfoById/{id}")
    public Response<AccountGroupInfoVo> getAccountGroupInfoById(@PathVariable("id") Long id) {
        Response<AccountGroupInfoVo> vo = accountGroupInfoApi.getAccountGroupInfoById(id);
        return vo;
    }

    @ApiOperation("更新 账号-组-实例")
    @PutMapping("/updateAccountGroupInfo")
    public Response updateAccountGroup(@RequestBody AccountOrgGroupDTO accountOrgGroupDTO) {
        Response<Boolean> flag = accountGroupInfoApi.updateAccountGroupInfo(accountOrgGroupDTO);
        return Response.ok(flag);
    }

    @ApiOperation("删除单个 账号-组-实例")
    @DeleteMapping("/deleteAccountGroupInfoById/{id}")
    public Response deleteAccountGroup(@PathVariable("id") Long id) {
        Response<Boolean> flag = accountGroupInfoApi.deleteAccountGroupInfoById(id.toString());
        return Response.ok(flag);
    }

    @ApiOperation("批量删除 账号-组-实例")
    @DeleteMapping("/batchDeleteGroupInfos")
    public Response batchDeleteGroups(@RequestParam("ids") List<String> ids) {
        Response<Boolean> flag = accountGroupInfoApi.batchDeleteGroupInfos(ids);
        return Response.ok(flag);
    }
}

