package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountTenantMapper;
import org.dows.account.entity.AccountTenant;
import org.dows.account.service.AccountTenantService;
import org.springframework.stereotype.Service;


/**
 * 账号-租户(AccountTenant)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@DS("uim")
@Service("accountTenantService")
public class AccountTenantServiceImpl extends MybatisCrudServiceImpl<AccountTenantMapper, AccountTenant> implements AccountTenantService {

}

