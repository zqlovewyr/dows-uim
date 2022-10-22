package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacRoleMapper;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.stereotype.Service;


/**
 * rbac-角色(RbacRole)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:19
 */
@Service("rbacRoleService")
public class RbacRoleServiceImpl extends MybatisCrudServiceImpl<RbacRoleMapper, RbacRole> implements RbacRoleService {

}

