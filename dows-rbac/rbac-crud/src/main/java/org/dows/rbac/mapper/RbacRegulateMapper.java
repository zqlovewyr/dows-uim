package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacRegulate;

/**
 * rbac-数据约束(RbacRegulate)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@Mapper
public interface RbacRegulateMapper extends MybatisCrudMapper<RbacRegulate> {

}

