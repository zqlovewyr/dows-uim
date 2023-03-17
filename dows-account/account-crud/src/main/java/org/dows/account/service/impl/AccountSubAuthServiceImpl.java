package org.dows.account.service.impl;

import org.dows.account.entity.AccountSubAuth;
import org.dows.account.mapper.AccountSubAuthMapper;
import org.dows.account.service.AccountSubAuthService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 子账号(AccountSub)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@Service("accountSubAuthService")
public class AccountSubAuthServiceImpl extends MybatisCrudServiceImpl<AccountSubAuthMapper, AccountSubAuth> implements AccountSubAuthService {

}

