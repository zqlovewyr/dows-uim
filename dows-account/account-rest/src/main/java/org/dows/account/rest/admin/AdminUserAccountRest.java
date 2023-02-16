package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountBiz;
import org.dows.account.bo.AccountCouponBo;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.bo.AccountOrderBo;
import org.dows.account.form.AccountCouponForm;
import org.dows.account.form.AccountInstanceTenantForm;
import org.dows.account.form.AccountOrderForm;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.*;
import org.dows.framework.api.Response;
import org.dows.order.form.OrderTaPageForm;
import org.dows.order.form.OrderTaTypeForm;
import org.dows.order.vo.OrderTaPackVo;
import org.dows.order.vo.OrderTaTableVo;
import org.dows.order.vo.OrderTaTakeOutVo;
import org.dows.order.vo.OrderTaVo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "B端-API-客户")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("admin/user/account")
public class AdminUserAccountRest {

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
    public Response<OrderTaVo> selectOrderStatistics(@RequestBody OrderTaTypeForm orderTaTypeForm){
        return Response.ok(accountBiz.selectOrderStatistics(orderTaTypeForm));
    }

    @PostMapping("/getTaOrderTablePage")
    @ApiOperation(value = "Ta订单 堂食列表")
    public Response<List<OrderTaTableVo>> getTaOrderTablePage(@RequestBody OrderTaPageForm orderTaPageForm){
        return Response.ok(accountBiz.getTaOrderTablePage(orderTaPageForm));
    }

    @PostMapping("/getTaOrderPackPage")
    @ApiOperation(value = "Ta订单 打包列表")
    public Response<List<OrderTaPackVo>> getTaOrderPackPage(@RequestBody OrderTaPageForm orderTaPageForm){
        return Response.ok(accountBiz.getTaOrderPackPage(orderTaPageForm));
    }

    @PostMapping("/getTaOrderTakeOutPage")
    @ApiOperation(value = "Ta订单 外卖列表")
    public Response<List<OrderTaTakeOutVo>> getTaOrderTakeOutPage(@RequestBody OrderTaPageForm orderTaPageForm){
        return Response.ok(accountBiz.getTaOrderTakeOutPage(orderTaPageForm));
    }

    @PostMapping("/selectAccountCouponPage")
    @ApiOperation(value = "用户分页优惠券列表")
    public Response<IPage<AccountCouponVo>> selectAccountCouponPage(@RequestBody AccountCouponForm accountCouponForm){
        AccountCouponBo accountCouponBo = new AccountCouponBo();
        BeanUtils.copyProperties(accountCouponForm,accountCouponBo);
        return Response.ok(accountBiz.selectAccountCouponPage(accountCouponBo));
    }

    @PostMapping("/selectAccountCouponStatistics")
    @ApiOperation(value = "优惠券列表页统计")
    public Response<Map<String,Object>> selectAccountCouponStatistics(@RequestBody AccountCouponForm accountCouponForm){
        AccountCouponBo accountCouponBo = new AccountCouponBo();
        BeanUtils.copyProperties(accountCouponForm,accountCouponBo);
        return Response.ok(accountBiz.selectAccountCouponStatistics(accountCouponBo));
    }

    @GetMapping("/selectAccountIntegralPage")
    @ApiOperation(value = "用户分页积分列表")
    public Response<IPage<AccountIntegralVo>> selectAccountIntegralPage(@RequestParam String accountId){
        return Response.ok(accountBiz.selectAccountIntegralPage(accountId));
    }

    @GetMapping("/selectAccountCouponStatistics")
    @ApiOperation(value = "积分列表页统计")
    public Response<Map<String,Object>> selectAccountCouponStatistics(@RequestParam String accountId){
        return Response.ok(accountBiz.selectAccountIntegralStatistics(accountId));
    }

    @PostMapping("/selectAccountTenantStatistics")
    @ApiOperation(value = "用户统计")
    public Response<Map<String,Object>> selectAccountTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
       BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        return Response.ok(accountBiz.selectAccountTenantStatistics(accountInstanceTenantBo));
    }

    @PostMapping("/selectAccountDistributionTenantStatistics")
    @ApiOperation(value = "客户分布统计")
    public Response<List<AccountDistributionVo>> selectAccountDistributionTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
        BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        return Response.ok(accountBiz.selectAccountDistributionTenantStatistics(accountInstanceTenantBo));
    }

    @PostMapping("/selectAccountConsumptionTenantStatistics")
    @ApiOperation(value = "消费能力统计")
    public Response<List<AccountConsumptionVo>> selectAccountConsumptionTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
        BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        return Response.ok(accountBiz.selectAccountConsumptionTenantStatistics(accountInstanceTenantBo));
    }
}
