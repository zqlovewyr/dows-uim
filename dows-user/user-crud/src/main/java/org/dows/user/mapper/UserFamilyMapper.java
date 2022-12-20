package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserFamily;

/**
 * 用户-家庭(UserFamily)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:16
 */
@Mapper
public interface UserFamilyMapper extends MybatisCrudMapper<UserFamily> {

}

