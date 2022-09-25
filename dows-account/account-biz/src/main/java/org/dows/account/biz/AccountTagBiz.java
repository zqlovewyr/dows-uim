package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.service.AccountTagService;
import org.springframework.stereotype.Service;

/**
 * 账号-标签维度信息(AccountTag)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:43
 */
@RequiredArgsConstructor
@Service
public class AccountTagBiz {
    private final AccountTagService service;

}
