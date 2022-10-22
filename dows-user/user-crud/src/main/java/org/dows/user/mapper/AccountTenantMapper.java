package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.AccountTenant;

/**
 * 账号-租户(AccountTenant)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Mapper
public interface AccountTenantMapper extends MybatisCrudMapper<AccountTenant> {

}

