package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserDwelling;

/**
 * 用户-住所(UserDwelling)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@Mapper
public interface UserDwellingMapper extends MybatisCrudMapper<UserDwelling> {

}

