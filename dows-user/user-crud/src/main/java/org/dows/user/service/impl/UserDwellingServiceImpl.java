package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.UserDwelling;
import org.dows.user.mapper.UserDwellingMapper;
import org.dows.user.service.UserDwellingService;
import org.springframework.stereotype.Service;


/**
 * 用户-住所(UserDwelling)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:15
 */
@DS("uim")
@Service("userDwellingService")
public class UserDwellingServiceImpl extends MybatisCrudServiceImpl<UserDwellingMapper, UserDwelling> implements UserDwellingService {

}

