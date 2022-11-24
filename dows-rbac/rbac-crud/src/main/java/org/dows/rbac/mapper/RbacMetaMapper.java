package org.dows.rbac.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.entity.RbacMeta;

/**
 * rbac-元数据(RbacMeta)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:46
 */
@Mapper
public interface RbacMetaMapper extends MybatisCrudMapper<RbacMeta> {

}

