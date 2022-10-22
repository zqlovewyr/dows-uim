package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UserAddressMapper;
import org.dows.user.entity.UserAddress;
import org.dows.user.service.UserAddressService;
import org.springframework.stereotype.Service;


/**
 * 用户-地址维度(UserAddress)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:10
 */
@Service("userAddressService")
public class UserAddressServiceImpl extends MybatisCrudServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}

