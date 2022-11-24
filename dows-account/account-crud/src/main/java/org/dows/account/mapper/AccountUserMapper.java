package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountUser;

/**
 * 账号-用户(实名认证后)(AccountUser)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Mapper
public interface AccountUserMapper extends MybatisCrudMapper<AccountUser> {

}

