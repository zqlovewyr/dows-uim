package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.crud.service.AccountIdentifierService;
import org.springframework.stereotype.Service;

/**
 * 账号-标识(AccountIdentifier)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:20
 */
@RequiredArgsConstructor
@Service
public class AccountIdentifierBiz {
    private final AccountIdentifierService service;

}
