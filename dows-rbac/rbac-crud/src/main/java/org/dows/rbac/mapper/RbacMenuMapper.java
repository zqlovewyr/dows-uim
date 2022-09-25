package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacMenu;

/**
 * RBAC-菜单资源(RbacMenu)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:16
 */
@Mapper
public interface RbacMenuMapper extends MybatisCrudMapper<RbacMenu> {

}
