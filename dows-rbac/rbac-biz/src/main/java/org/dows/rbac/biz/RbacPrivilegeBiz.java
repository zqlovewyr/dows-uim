package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.service.RbacPrivilegeService;
import org.springframework.stereotype.Service;

/**
 * RBAC权限(RbacPrivilege)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
public class RbacPrivilegeBiz {
    private final RbacPrivilegeService service;

}
