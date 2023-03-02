package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.service.UserAddressService;
import org.dows.user.mapper.UserAddressMapper;
import org.dows.user.entity.UserAddress;
import org.springframework.stereotype.Service;


/**
 * 用户-地址维度(UserAddress)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@DS("uim")
@Service("userAddressService")
public class UserAddressServiceImpl extends MybatisCrudServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}

