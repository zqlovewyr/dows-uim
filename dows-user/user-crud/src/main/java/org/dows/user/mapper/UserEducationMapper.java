package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserEducation;

/**
 * 用户-教育信息(UserEducation)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:15
 */
@Mapper
public interface UserEducationMapper extends MybatisCrudMapper<UserEducation> {

}

