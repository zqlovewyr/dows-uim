package org.dows.account.rest.tenant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountBiz;
import org.dows.account.bo.AccountCouponBo;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.bo.IffSettingBo;
import org.dows.account.form.AccountCouponForm;
import org.dows.account.form.AccountInstanceTenantForm;
import org.dows.account.form.IffSettingForm;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "租户用户统计")
    public Response<Map<String,Object>> selectAccountTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
       BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        return Response.ok(accountBiz.selectAccountTenantStatistics(accountInstanceTenantBo));
    }

    @PostMapping("/selectAccountDistributionTenantStatistics")
    @ApiOperation(value = "租户客户分布统计")
    public Response<List<AccountDistributionVo>> selectAccountDistributionTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
        BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        return Response.ok(accountBiz.selectAccountDistributionTenantStatistics(accountInstanceTenantBo));
    }

    @PostMapping("/selectAccountConsumptionTenantStatistics")
    @ApiOperation(value = "租户消费能力统计")
    public Response<List<AccountConsumptionVo>> selectAccountConsumptionTenantStatistics(@RequestBody AccountInstanceTenantForm accountInstanceTenantForm){
        AccountInstanceTenantBo accountInstanceTenantBo = new AccountInstanceTenantBo();
        BeanUtils.copyProperties(accountInstanceTenantForm,accountInstanceTenantBo);
        accountInstanceTenantBo.setStoreId("1"); // TODO
        return Response.ok(accountBiz.selectAccountConsumptionTenantStatistics(accountInstanceTenantBo));
    }

    @PostMapping("/selectIffSettingList")
    @ApiOperation(value = "设置消费能力金额-查询")
    public Response<List<IffSettingVo>> selectIffSettingList(@RequestBody IffSettingForm iffSettingForm){
        IffSettingBo iffSettingBo = new IffSettingBo();
        BeanUtils.copyProperties(iffSettingForm,iffSettingBo);
        iffSettingBo.setStoreId("1");
        return Response.ok(accountBiz.selectIffSettingList(iffSettingBo,100001));
    }

    @PostMapping("/saveIffSettingList")
    @ApiOperation(value = "设置消费能力金额-新增")
    public Response<Boolean> saveIffSettingList(@RequestBody List<IffSettingForm> iffSettingForms){
        List<IffSettingBo> iffSettingBos = new ArrayList<>();
        iffSettingForms.stream().forEach(item ->{
            IffSettingBo iffSettingBo = new IffSettingBo();
            BeanUtils.copyProperties(item,iffSettingBo);
            iffSettingBo.setStoreId("1");
            iffSettingBos.add(iffSettingBo);
        });
        // TODO
        return Response.ok(accountBiz.saveIffSettingList(iffSettingBos,"1",100001));
    }
    @PostMapping("/selectIffSettingConsumeList")
    @ApiOperation(value = "未设置消费天数-查询")
    public Response<List<IffSettingVo>> selectIffSettingConsumeList(@RequestBody IffSettingForm iffSettingForm){
        IffSettingBo iffSettingBo = new IffSettingBo();
        BeanUtils.copyProperties(iffSettingForm,iffSettingBo);
        iffSettingBo.setStoreId("1");
        return Response.ok(accountBiz.selectIffSettingList(iffSettingBo,100002));
    }

    @PostMapping("/saveIffSettingConsumeList")
    @ApiOperation(value = "未设置消费天数-新增")
    public Response<Boolean> saveIffSettingConsumeList(@RequestBody List<IffSettingForm> iffSettingForms){
        List<IffSettingBo> iffSettingBos = new ArrayList<>();
        iffSettingForms.stream().forEach(item ->{
            IffSettingBo iffSettingBo = new IffSettingBo();
            BeanUtils.copyProperties(item,iffSettingBo);
            iffSettingBo.setStoreId("1");
            iffSettingBos.add(iffSettingBo);
        });
        // TODO
        return Response.ok(accountBiz.saveIffSettingList(iffSettingBos,"1",100002));
    }
}
