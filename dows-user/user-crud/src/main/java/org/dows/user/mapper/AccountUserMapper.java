package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountUser;

/**
 * 账号-用户(实名认证后)(AccountUser)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@Mapper
public interface AccountUserMapper extends MybatisCrudMapper<AccountUser> {

}

