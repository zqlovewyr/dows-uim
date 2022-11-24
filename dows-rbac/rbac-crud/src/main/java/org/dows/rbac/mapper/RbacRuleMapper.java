package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacRule;

/**
 * rbac-数据规则(RbacRule)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:48
 */
@Mapper
public interface RbacRuleMapper extends MybatisCrudMapper<RbacRule> {

}

