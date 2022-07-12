package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.crud.service.PrincipalRoleService;
import org.springframework.stereotype.Service;

/**
 * 账号-主体角色维度信息(PrincipalRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:45:17
 */
@RequiredArgsConstructor
@Service
public class PrincipalRoleBiz {
    private final PrincipalRoleService service;

}
