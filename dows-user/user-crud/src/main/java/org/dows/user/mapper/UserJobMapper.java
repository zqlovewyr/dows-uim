package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserJob;

/**
 * 用户-工作信息(UserJob)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
@Mapper
public interface UserJobMapper extends MybatisCrudMapper<UserJob> {

}

