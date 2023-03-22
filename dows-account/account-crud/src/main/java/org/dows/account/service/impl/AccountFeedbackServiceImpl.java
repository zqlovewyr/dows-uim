package org.dows.account.service.impl;

import org.dows.account.entity.AccountFeedback;
import org.dows.account.mapper.AccountFeedbackMapper;
import org.dows.account.service.AccountFeedbackService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 账号-反馈(实名认证后)(AccountFeedback)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Service("accountFeedbackService")
public class AccountFeedbackServiceImpl extends MybatisCrudServiceImpl<AccountFeedbackMapper, AccountFeedback> implements AccountFeedbackService {

}

