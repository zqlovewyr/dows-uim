package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacRole;

/**
 * RBAC-角色(RbacRole)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:21
 */
@Mapper
public interface RbacRoleMapper extends MybatisCrudMapper<RbacRole> {

}
