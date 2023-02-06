package org.dows.account.rest.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountBiz;
import org.dows.account.query.AccountQuery;
import org.dows.account.util.MyConstant;
import org.dows.account.vo.AccountVo;
import org.dows.framework.api.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public Response<IPage<AccountVo>> pageList(
            @ApiParam(value = "页码", required = true) Integer page,
            @ApiParam(value = "每页数据条数", required = true) Integer size,
            @ApiParam(value = "会员名称") @RequestParam(required = false) String accountName,
            @ApiParam(value = "会员活跃度true升序false降序") @RequestParam(required = false) Boolean activeSort,
            @ApiParam(value = "注册开始时间yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT) LocalDate createStartDate,
            @ApiParam(value = "注册截止时间yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT) LocalDate createEndDate,
            @ApiParam(value = "生日开始时间yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT) LocalDate birthdayStartDate,
            @ApiParam(value = "生日截止时间yyyy-MM-dd") @RequestParam(required = false) @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT) LocalDate birthdayEndDate,
            @ApiParam(value = "是否筛选信息全的客户") @RequestParam(required = false, defaultValue = "false") Boolean infoIfFull,
            @ApiParam(value = "消费金额排序true升序false降序") @RequestParam(required = false) Boolean moneySort,
            @ApiParam(value = "最后下单时间排序true升序false降序") @RequestParam(required = false) Boolean lastOrderTimeSort){
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setPage(page);
        accountQuery.setSize(size);
        accountQuery.setBirthdayStartDate(birthdayStartDate);
        accountQuery.setBirthdayEndDate(birthdayEndDate);
        accountQuery.setCreateStartDate(createStartDate);
        accountQuery.setCreateEndDate(createEndDate);
        accountQuery.setInfoIfFull(infoIfFull);
        accountQuery.setMoneySort(moneySort);
        accountQuery.setLastOrderTimeSort(lastOrderTimeSort);
        accountQuery.setAccountName(accountName);
        accountQuery.setActiveSort(activeSort);
        return accountBiz.getAccuntListPage(accountQuery);
    }

    @GetMapping("{accountId}/info")
    @ApiOperation(value = "根据客户账号id获取详情")
    public Response<AccountVo> getInfo(
            @ApiParam(required = true, value = "账号id") @PathVariable("accountId") String accountId){
        return Response.ok(accountBiz.getInfoByAccountId(accountId));
    }
}
