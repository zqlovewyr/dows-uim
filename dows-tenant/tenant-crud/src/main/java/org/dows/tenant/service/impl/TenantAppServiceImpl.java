package org.dows.tenant.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.tenant.mapper.TenantAppMapper;
import org.dows.tenant.entity.TenantApp;
import org.dows.tenant.service.TenantAppService;
import org.springframework.stereotype.Service;


/**
 * 租户-应用(TenantApp)表服务实现类
 *
 * @author lait
 * @since 2023-01-08 16:35:30
 */
@DS("uim")
@Service("tenantAppService")
public class TenantAppServiceImpl extends MybatisCrudServiceImpl<TenantAppMapper, TenantApp> implements TenantAppService {

}

