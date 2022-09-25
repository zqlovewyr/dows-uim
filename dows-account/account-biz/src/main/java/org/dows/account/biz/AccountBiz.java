package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.api.dto.AccountInfo;
import org.dows.account.service.AccountInstanceService;
import org.springframework.stereotype.Service;

/**
 * 账号-实例维度信息(AccountInstance)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:25
 */
@RequiredArgsConstructor
@Service
public class AccountBiz {
    private final AccountInstanceService service;

    public AccountInfo findAccount(String username) {

        return new AccountInfo();
    }
}
