package org.dows.rbac.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.crud.entity.RbacAccredit;

/**
 * RBAC-角色授权(RbacAccredit)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:10
 */
@Mapper
public interface RbacAccreditMapper extends MybatisCrudMapper<RbacAccredit> {

}
