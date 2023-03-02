package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UserContactMapper;
import org.dows.user.entity.UserContact;
import org.dows.user.service.UserContactService;
import org.springframework.stereotype.Service;


/**
 * 用户-联系人(UserContact)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@DS("uim")
@Service("userContactService")
public class UserContactServiceImpl extends MybatisCrudServiceImpl<UserContactMapper, UserContact> implements UserContactService {

}

