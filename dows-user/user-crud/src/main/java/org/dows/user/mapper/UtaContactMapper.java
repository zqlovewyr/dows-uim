package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UtaContact;

/**
 * 联系需求发布者(UtaContact)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@Mapper
public interface UtaContactMapper extends MybatisCrudMapper<UtaContact> {

}

