package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacMeta;

/**
 * rbac-元数据(RbacMeta)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:17
 */
@Mapper
public interface RbacMetaMapper extends MybatisCrudMapper<RbacMeta> {

}

