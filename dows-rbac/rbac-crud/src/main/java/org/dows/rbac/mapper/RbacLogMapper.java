package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacLog;

/**
 * rbac-日志(RbacLog)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:44
 */
@Mapper
public interface RbacLogMapper extends MybatisCrudMapper<RbacLog> {

}

