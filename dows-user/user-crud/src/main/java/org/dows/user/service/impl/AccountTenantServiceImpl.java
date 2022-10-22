package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountTenantMapper;
import org.dows.user.entity.AccountTenant;
import org.dows.user.service.AccountTenantService;
import org.springframework.stereotype.Service;


/**
 * 账号-租户(AccountTenant)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Service("accountTenantService")
public class AccountTenantServiceImpl extends MybatisCrudServiceImpl<AccountTenantMapper, AccountTenant> implements AccountTenantService {

}

