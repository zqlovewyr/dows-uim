package org.dows.rbac.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.crud.entity.RbacUri;

/**
 * RBAC-功能资源(RbacUri)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:24
 */
@Mapper
public interface RbacUriMapper extends MybatisCrudMapper<RbacUri> {

}
