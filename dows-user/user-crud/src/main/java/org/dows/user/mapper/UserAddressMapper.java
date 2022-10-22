package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserAddress;

/**
 * 用户-地址维度(UserAddress)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:10
 */
@Mapper
public interface UserAddressMapper extends MybatisCrudMapper<UserAddress> {

}

