package org.dows.tenant.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.tenant.mapper.TenantInstanceMapper;
import org.dows.tenant.entity.TenantInstance;
import org.dows.tenant.service.TenantInstanceService;
import org.springframework.stereotype.Service;


/**
 * 租户-实例(TenantInstance)表服务实现类
 *
 * @author lait
 * @since 2023-01-08 16:35:30
 */
@Service("tenantInstanceService")
public class TenantInstanceServiceImpl extends MybatisCrudServiceImpl<TenantInstanceMapper, TenantInstance> implements TenantInstanceService {

}

