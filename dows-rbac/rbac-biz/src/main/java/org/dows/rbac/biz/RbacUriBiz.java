package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.crud.service.RbacUriService;
import org.springframework.stereotype.Service;

/**
 * RBAC-功能资源(RbacUri)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:24
 */
@RequiredArgsConstructor
@Service
public class RbacUriBiz {
    private final RbacUriService service;

}
