package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacPermissionMapper;
import org.dows.rbac.entity.RbacPermission;
import org.dows.rbac.service.RbacPermissionService;
import org.springframework.stereotype.Service;


/**
 * rbac-权限(RbacPermission)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@Service("rbacPermissionService")
public class RbacPermissionServiceImpl extends MybatisCrudServiceImpl<RbacPermissionMapper, RbacPermission> implements RbacPermissionService {

}

