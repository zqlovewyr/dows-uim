package org.dows.rbac.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.query.AccountOrgQuery;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.vo.RbacRoleVo;

/**
 * rbac-角色(RbacRole)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:47
 */
@Mapper
public interface RbacRoleMapper extends MybatisCrudMapper<RbacRole> {

    IPage<RbacRoleVo> selectRbacRolePage(IPage<RbacRoleVo> page, @Param("rbacRoleQuery") RbacRoleQuery rbacRoleQuery);
}

