package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountFeedback;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 账号-反馈(实名认证后)(AccountFeedback)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Mapper
public interface AccountFeedbackMapper extends MybatisCrudMapper<AccountFeedback> {

}

