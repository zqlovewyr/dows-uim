package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.crud.service.RbacExprService;
import org.springframework.stereotype.Service;

/**
 * rbac-权限表达式(RbacExpr)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:13
 */
@RequiredArgsConstructor
@Service
public class RbacExprBiz {
    private final RbacExprService service;

}
