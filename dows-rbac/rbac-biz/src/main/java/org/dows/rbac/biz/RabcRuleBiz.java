package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.crud.service.RabcRuleService;
import org.springframework.stereotype.Service;

/**
 * RBAC-权限规则(RabcRule)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:06
 */
@RequiredArgsConstructor
@Service
public class RabcRuleBiz {
    private final RabcRuleService service;

}
