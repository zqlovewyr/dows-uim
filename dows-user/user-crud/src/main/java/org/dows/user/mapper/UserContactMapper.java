package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserContact;

/**
 * 用户-联系人(UserContact)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@Mapper
public interface UserContactMapper extends MybatisCrudMapper<UserContact> {

}

