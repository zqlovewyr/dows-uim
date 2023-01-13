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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

