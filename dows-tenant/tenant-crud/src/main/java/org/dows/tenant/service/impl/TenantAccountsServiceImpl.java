package org.dows.tenant.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.tenant.mapper.TenantAccountsMapper;
import org.dows.tenant.entity.TenantAccounts;
import org.dows.tenant.service.TenantAccountsService;
import org.springframework.stereotype.Service;


/**
 * 租户-账号(TenantAccounts)表服务实现类
 *
 * @author lait
 * @since 2023-01-08 16:35:29
 */
@DS("uim")
@Service("tenantAccountsService")
public class TenantAccountsServiceImpl extends MybatisCrudServiceImpl<TenantAccountsMapper, TenantAccounts> implements TenantAccountsService {

}

