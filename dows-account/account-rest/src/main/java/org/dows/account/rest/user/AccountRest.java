package org.dows.account.rest.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountBiz;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountVo;
import org.dows.auth.biz.context.SecurityUtils;
import org.dows.framework.api.Response;
import org.dows.marketing.form.MarketCouponQueryForm;
import org.dows.marketing.form.MarketListCouponVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "API-客户")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user/account")
public class AccountRest {

    private final AccountBiz accountBiz;

    @GetMapping("test")
    public Response test() {
        return Response.ok("succe ss");
    }

    @GetMapping("page")
    @ApiOperation(value = "分页获取客户列表")
    public Response<IPage<AccountVo>> pageList(@Valid @RequestBody AccountQuery accountQuery){
        return accountBiz.getAccuntListPage(accountQuery);
    }

    @GetMapping("/info")
    @ApiOperation(value = "根据客户账号id获取详情")
    public Response<AccountVo> getInfo(){
        String accountId = SecurityUtils.getAccountId();
        return Response.ok(accountBiz.getInfoByAccountId(accountId));
    }

    @PostMapping("/selectAccountCouponPage")
    @ApiOperation(value = "用户分页优惠券列表")
    public Response<IPage<MarketListCouponVo>> selectAccountCouponPage(@RequestBody MarketCouponQueryForm accountCouponForm){
        return Response.ok(accountBiz.getCouponList(accountCouponForm));
    }
}
