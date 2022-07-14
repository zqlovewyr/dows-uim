package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.crud.service.RbacRoleService;
import org.springframework.stereotype.Service;

/**
 * RBAC-角色(RbacRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
public class RbacRoleBiz {
    private final RbacRoleService service;

}
