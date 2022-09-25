package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.service.RbacMenuService;
import org.springframework.stereotype.Service;

/**
 * RBAC-菜单资源(RbacMenu)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:15
 */
@RequiredArgsConstructor
@Service
public class RbacMenuBiz {
    private final RbacMenuService service;

}
