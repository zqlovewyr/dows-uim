package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.crud.service.AccountGroupService;
import org.springframework.stereotype.Service;

/**
 * 账号-账号组维度信息(AccountGroup)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
@RequiredArgsConstructor
@Service
public class AccountGroupBiz {
    private final AccountGroupService service;

}
