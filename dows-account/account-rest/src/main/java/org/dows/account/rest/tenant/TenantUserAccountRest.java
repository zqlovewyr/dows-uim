package org.dows.account.rest.tenant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountBiz;
import org.dows.account.bo.AccountOrderBo;
import org.dows.account.form.AccountOrderForm;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountOrderStatisticsVo;
import org.dows.account.vo.AccountOrderVo;
import org.dows.account.vo.AccountVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "B端-API-客户")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("tenant/user/account")
public class TenantUserAccountRest {

    private final AccountBiz accountBiz;

    @GetMapping("test")
    public Response test() {
        return Response.ok("success");
    }

    @PostMapping("page")
    @ApiOperation(value = "分页获取客户列表")
    public Response<IPage<AccountVo>> pageList(@Valid @RequestBody AccountQuery accountQuery){
        return accountBiz.getAccuntListPage(accountQuery);
    }

    @GetMapping("{accountId}/info")
    @ApiOperation(value = "根据客户账号id获取详情")
    public Response<AccountVo> getInfo(
            @ApiParam(required = true, value = "账号id") @PathVariable("accountId") String accountId){
        return Response.ok(accountBiz.getInfoByAccountId(accountId));
    }

    @PostMapping("/selectOrderStatistics")
    @ApiOperation(value = "用户订单量统计")
    public Response<AccountOrderStatisticsVo> selectOrderStatistics(@RequestBody AccountOrderForm accountOrderForm){
        AccountOrderBo accountOrderBo = new AccountOrderBo();
        BeanUtils.copyProperties(accountOrderForm,accountOrderBo);
        return Response.ok(accountBiz.selectOrderStatistics(accountOrderBo));
    }

    @PostMapping("/selectOrderInstancePage")
    @ApiOperation(value = "用户分页订单列表")
    public Response<IPage<AccountOrderVo>> selectOrderInstancePage(@RequestBody AccountOrderForm accountOrderForm){
        AccountOrderBo accountOrderBo = new AccountOrderBo();
        BeanUtils.copyProperties(accountOrderForm,accountOrderBo);
        return Response.ok(accountBiz.selectOrderInstancePage(accountOrderBo));
    }
}
