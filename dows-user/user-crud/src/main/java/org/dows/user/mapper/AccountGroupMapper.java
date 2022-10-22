package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountGroup;

/**
 * 账号-组(AccountGroup)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:24
 */
@Mapper
public interface AccountGroupMapper extends MybatisCrudMapper<AccountGroup> {

}

