package org.dows.account.service.impl;

import org.dows.account.entity.AccountSub;
import org.dows.account.mapper.AccountSubMapper;
import org.dows.account.service.AccountSubService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 子账号(AccountSub)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@Service("accountSubService")
public class AccountSubServiceImpl extends MybatisCrudServiceImpl<AccountSubMapper, AccountSub> implements AccountSubService {

}

