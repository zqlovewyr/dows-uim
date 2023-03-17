package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.AccountSub;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 子账号(AccountSub)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@Mapper
public interface AccountSubMapper extends MybatisCrudMapper<AccountSub> {

}

