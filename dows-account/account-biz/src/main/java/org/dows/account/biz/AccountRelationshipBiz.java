package org.dows.account.biz;

import lombok.RequiredArgsConstructor;
import org.dows.account.service.AccountRelationshipService;
import org.springframework.stereotype.Service;

/**
 * 账号-关系维度信息(AccountRelationship)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:40
 */
@RequiredArgsConstructor
@Service
public class AccountRelationshipBiz {
    private final AccountRelationshipService service;

}
