package org.dows.tenant.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.tenant.entity.TenantApp;

/**
 * 租户-应用(TenantApp)表数据库访问层
 *
 * @author lait
 * @since 2023-01-08 16:35:30
 */
@Mapper
public interface TenantAppMapper extends MybatisCrudMapper<TenantApp> {

}

