package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RabcRule;
import org.dows.rbac.crud.mapper.RabcRuleMapper;
import org.dows.rbac.crud.service.RabcRuleService;
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
