package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountUserMapper;
import org.dows.user.entity.AccountUser;
import org.dows.user.service.AccountUserService;
import org.springframework.stereotype.Service;


/**
 * 账号-用户(实名认证后)(AccountUser)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@Service("accountUserService")
public class AccountUserServiceImpl extends MybatisCrudServiceImpl<AccountUserMapper, AccountUser> implements AccountUserService {

}

