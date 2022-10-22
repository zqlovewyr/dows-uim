package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacRuleMapper;
import org.dows.rbac.entity.RbacRule;
import org.dows.rbac.service.RbacRuleService;
import org.springframework.stereotype.Service;


/**
 * rbac-数据规则(RbacRule)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:19
 */
@Service("rbacRuleService")
public class RbacRuleServiceImpl extends MybatisCrudServiceImpl<RbacRuleMapper, RbacRule> implements RbacRuleService {

}

