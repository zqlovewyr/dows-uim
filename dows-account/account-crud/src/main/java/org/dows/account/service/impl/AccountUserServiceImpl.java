package org.dows.account.service.impl;

import org.dows.account.service.AccountUserService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.entity.AccountUser;
import org.dows.account.mapper.AccountUserMapper;
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

