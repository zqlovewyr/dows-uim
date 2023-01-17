package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacMenu;
import org.dows.rbac.query.RbacMenuQuery;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.vo.RbacMenuVo;

import java.util.List;

/**
 * rbac-菜单(RbacMenu)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:45
 */
@Mapper
public interface RbacMenuMapper extends MybatisCrudMapper<RbacMenu> {

    public List<RbacMenuVo> selectMenuListByAccountId(@Param("rbacMenuQuery") RbacMenuQuery rbacMenuQuery);

    public List<RbacMenuVo> selectMenuList(@Param("rbacMenuQuery") RbacMenuQuery rbacMenuQuery);
}

