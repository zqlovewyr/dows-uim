package org.dows.rbac.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RabcRule;
import org.dows.rbac.mapper.RabcRuleMapper;
import org.dows.rbac.service.RabcRuleService;
import org.springframework.stereotype.Service;

/**
 * RBAC-权限规则(RabcRule)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:46
 */
@Service
public class RabcRuleServiceImpl extends MybatisCrudServiceImpl<RabcRuleMapper, RabcRule> implements RabcRuleService {

}
