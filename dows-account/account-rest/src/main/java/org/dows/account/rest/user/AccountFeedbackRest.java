package org.dows.account.rest.user;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.entity.AccountFeedback;
import org.dows.account.form.AccountFeedbackForm;
import org.dows.account.service.AccountFeedbackService;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号-用户反馈控制层
 *
 * @author lait.zhang
 * @since 2022-12-23 22:05:09
 */
@Api(tags = "用户反馈")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user/account/feedback")
public class AccountFeedbackRest implements MybatisCrudRest<AccountFeedbackForm, AccountFeedback, AccountFeedbackService> {
    
}

