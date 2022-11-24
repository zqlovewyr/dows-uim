package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountGroup;

/**
 * 账号-组(AccountGroup)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@Mapper
public interface AccountGroupMapper extends MybatisCrudMapper<AccountGroup> {

}

