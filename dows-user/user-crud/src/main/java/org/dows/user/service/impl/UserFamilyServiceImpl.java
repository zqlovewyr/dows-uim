package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.UserFamily;
import org.dows.user.mapper.UserFamilyMapper;
import org.dows.user.service.UserFamilyService;
import org.springframework.stereotype.Service;


/**
 * 用户-家庭(UserFamily)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
@DS("uim")
@Service("userFamilyService")
public class UserFamilyServiceImpl extends MybatisCrudServiceImpl<UserFamilyMapper, UserFamily> implements UserFamilyService {

}

