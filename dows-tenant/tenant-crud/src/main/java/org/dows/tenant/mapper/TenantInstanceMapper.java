package org.dows.tenant.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.tenant.entity.TenantInstance;

/**
 * 租户-实例(TenantInstance)表数据库访问层
 *
 * @author lait
 * @since 2023-01-08 16:35:30
 */
@Mapper
public interface TenantInstanceMapper extends MybatisCrudMapper<TenantInstance> {

}

