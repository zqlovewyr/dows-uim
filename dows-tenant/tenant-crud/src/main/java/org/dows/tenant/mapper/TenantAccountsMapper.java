package org.dows.tenant.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.tenant.entity.TenantAccounts;

/**
 * 租户-账号(TenantAccounts)表数据库访问层
 *
 * @author lait
 * @since 2023-01-08 16:35:29
 */
@Mapper
public interface TenantAccountsMapper extends MybatisCrudMapper<TenantAccounts> {

}

