package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RbacExpr;
import org.dows.rbac.crud.mapper.RbacExprMapper;
import org.dows.rbac.crud.service.RbacExprService;
import org.springframework.stereotype.Service;

/**
 * rbac-权限表达式(RbacExpr)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:47
 */
@Service
public class RbacExprServiceImpl extends MybatisCrudServiceImpl<RbacExprMapper, RbacExpr> implements RbacExprService {

}
