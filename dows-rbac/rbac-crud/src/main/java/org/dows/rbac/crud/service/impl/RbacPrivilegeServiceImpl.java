package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RbacPrivilege;
import org.dows.rbac.crud.mapper.RbacPrivilegeMapper;
import org.dows.rbac.crud.service.RbacPrivilegeService;
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
