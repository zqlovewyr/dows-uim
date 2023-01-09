package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UserCompanyMapper;
import org.dows.user.entity.UserCompany;
import org.dows.user.service.UserCompanyService;
import org.springframework.stereotype.Service;


/**
 * 用户-公司(UserCompany)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@Service("userCompanyService")
public class UserCompanyServiceImpl extends MybatisCrudServiceImpl<UserCompanyMapper, UserCompany> implements UserCompanyService {

}

