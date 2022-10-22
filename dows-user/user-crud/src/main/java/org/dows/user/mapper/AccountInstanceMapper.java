package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountInstance;

/**
 * 账号-实例(AccountInstance)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@Mapper
public interface AccountInstanceMapper extends MybatisCrudMapper<AccountInstance> {

}

