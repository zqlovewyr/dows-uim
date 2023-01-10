package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.UserEducation;
import org.dows.user.mapper.UserEducationMapper;
import org.dows.user.service.UserEducationService;
import org.springframework.stereotype.Service;


/**
 * 用户-教育信息(UserEducation)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:15
 */
@Service("userEducationService")
public class UserEducationServiceImpl extends MybatisCrudServiceImpl<UserEducationMapper, UserEducation> implements UserEducationService {

}

