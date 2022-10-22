package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UserInstanceMapper;
import org.dows.user.entity.UserInstance;
import org.dows.user.service.UserInstanceService;
import org.springframework.stereotype.Service;


/**
 * 用户-实例(UserInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@Service("userInstanceService")
public class UserInstanceServiceImpl extends MybatisCrudServiceImpl<UserInstanceMapper, UserInstance> implements UserInstanceService {

}

