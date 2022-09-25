package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacPrivilege;

/**
 * RBAC权限(RbacPrivilege)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@Mapper
public interface RbacPrivilegeMapper extends MybatisCrudMapper<RbacPrivilege> {

}
