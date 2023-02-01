package org.dows.account.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountOrgBiz;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.entity.AccountOrg;
import org.dows.account.form.AccountOrgForm;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
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

    private final AccountOrgBiz accountOrgBiz;

    @ApiOperation("保存树型 账号-组织")
    @PostMapping({"/createTreeAccountOrg"})
    public Response createTreeAccountOrg(@RequestBody TreeAccountOrgDTO treeAccountOrgDto) {
        accountOrgBiz.createTreeAccountOrg(treeAccountOrgDto);
        return Response.ok();
    }

    @ApiOperation("保存 账号-组织")
    @PostMapping({"/createAccountOrg"})
    public Response<AccountOrgVo> createAccountOrg(@RequestBody AccountOrgDTO accountOrgDTO) {
        return Response.ok(accountOrgBiz.createAccountOrg(accountOrgDTO));
    }

/*    @ApiOperation("查询 账号-组织 列表")
    @GetMapping({"/pageAccountOrg/{page}/{size}"})
    public Response<IPage<AccountOrgVo>> pageAccountOrg(@RequestParam("应用ID") String appId
            , @RequestParam("账号ID") String accountId
            , @RequestParam("账号名") String accountName
            , @PathVariable("page") Integer pageNo
            , @PathVariable("size") Integer pageSize) {
        return Response.ok(accountOrgBiz.pageAccountOrg(appId, accountId, accountName, pageNo, pageSize));
    }*/

}

