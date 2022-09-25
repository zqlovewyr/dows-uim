package org.dows.rbac.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RbacPrivilege;
import org.dows.rbac.mapper.RbacPrivilegeMapper;
import org.dows.rbac.service.RbacPrivilegeService;
import org.springframework.stereotype.Service;

/**
 * RBAC权限(RbacPrivilege)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:48
 */
@Service
public class RbacPrivilegeServiceImpl extends MybatisCrudServiceImpl<RbacPrivilegeMapper, RbacPrivilege> implements RbacPrivilegeService {

}
