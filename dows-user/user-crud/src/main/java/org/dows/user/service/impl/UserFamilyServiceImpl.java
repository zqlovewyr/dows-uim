package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UserFamilyMapper;
import org.dows.user.entity.UserFamily;
import org.dows.user.service.UserFamilyService;
import org.springframework.stereotype.Service;


/**
 * 用户-家庭(UserFamily)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
@Service("userFamilyService")
public class UserFamilyServiceImpl extends MybatisCrudServiceImpl<UserFamilyMapper, UserFamily> implements UserFamilyService {

}

