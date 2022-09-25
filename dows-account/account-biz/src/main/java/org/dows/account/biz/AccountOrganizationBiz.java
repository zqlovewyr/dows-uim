package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.service.AccountOrganizationService;
import org.springframework.stereotype.Service;

/**
 * 账号-组织架构维度信息(AccountOrganization)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:35
 */
@RequiredArgsConstructor
@Service
public class AccountOrganizationBiz {
    private final AccountOrganizationService service;

}
