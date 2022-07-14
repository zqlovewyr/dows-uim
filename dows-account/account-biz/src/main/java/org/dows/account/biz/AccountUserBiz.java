package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.crud.service.AccountUserService;
import org.springframework.stereotype.Service;

/**
 * 账号-用户维度信息(AccountUser)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:52
 */
@RequiredArgsConstructor
@Service
public class AccountUserBiz {
    private final AccountUserService service;

}
