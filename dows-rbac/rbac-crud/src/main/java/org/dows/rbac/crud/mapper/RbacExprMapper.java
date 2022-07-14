package org.dows.rbac.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.crud.entity.RbacExpr;

/**
 * rbac-权限表达式(RbacExpr)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:13
 */
@Mapper
public interface RbacExprMapper extends MybatisCrudMapper<RbacExpr> {

}
