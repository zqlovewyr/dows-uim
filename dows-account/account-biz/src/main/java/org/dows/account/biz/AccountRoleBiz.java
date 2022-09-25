package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.service.AccountRoleService;
import org.springframework.stereotype.Service;

/**
 * 账号-主体角色维度信息(AccountRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:41
 */
@RequiredArgsConstructor
@Service
public class AccountRoleBiz {
    private final AccountRoleService service;

}
