package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacRole;

/**
 * rbac-角色(RbacRole)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:47
 */
@Mapper
public interface RbacRoleMapper extends MybatisCrudMapper<RbacRole> {

}

