package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RabcRule;

/**
 * RBAC-权限规则(RabcRule)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:06
 */
@Mapper
public interface RabcRuleMapper extends MybatisCrudMapper<RabcRule> {

}
