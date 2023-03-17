package org.dows.account.rest.tenant;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountSubBiz;
import org.dows.account.entity.AccountSub;
import org.dows.account.form.AccountSubForm;
import org.dows.account.service.AccountSubService;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 子账号(AccountSub)表控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "子账号")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("tenant/accountsub")
public class TenantAccountSubRest implements MybatisCrudRest<AccountSubForm, AccountSub, AccountSubService> {

    private final AccountSubBiz accountSubBiz;

    @GetMapping(value = "/accountsubPage")
    @ApiOperation(value = "分页获取子账号")
    public Response accountsubPage(Page page, AccountSub accountSub) {
        return Response.ok(getService().page(page, Wrappers.lambdaQuery(accountSub).orderByDesc(AccountSub::getDt)));
    }


    @PostMapping(value = "/saveorupdate")
    @ApiOperation(value = "新增编辑子账号")
    public Response saveorupdate(@Valid  @RequestBody AccountSubForm from) {
        return accountSubBiz.saveorupdate(from);
    }


}

