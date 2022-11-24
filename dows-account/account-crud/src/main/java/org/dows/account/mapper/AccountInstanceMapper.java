package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountInstance;

/**
 * 账号-实例(AccountInstance)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountInstanceMapper extends MybatisCrudMapper<AccountInstance> {

}

