package org.dows.user.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.entity.AccountReferrals;
import org.dows.user.form.AccountReferralsForm;
import org.dows.user.service.AccountReferralsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推荐关系(AccountReferrals)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@Api(tags = "推荐关系")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("accountReferrals")
public class AccountReferralsRest implements MybatisCrudRest<AccountReferralsForm, AccountReferrals, AccountReferralsService> {

    //private final AccountReferralsBiz accountReferralsBiz;

}

