package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RbacRole;
import org.dows.rbac.crud.mapper.RbacRoleMapper;
import org.dows.rbac.crud.service.RbacRoleService;
import org.springframework.stereotype.Service;

/**
 * RBAC-角色(RbacRole)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:48
 */
@Service
public class RbacRoleServiceImpl extends MybatisCrudServiceImpl<RbacRoleMapper, RbacRole> implements RbacRoleService {

}
