package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacRegulateMapper;
import org.dows.rbac.entity.RbacRegulate;
import org.dows.rbac.service.RbacRegulateService;
import org.springframework.stereotype.Service;


/**
 * rbac-数据约束(RbacRegulate)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:47
 */
@Service("rbacRegulateService")
public class RbacRegulateServiceImpl extends MybatisCrudServiceImpl<RbacRegulateMapper, RbacRegulate> implements RbacRegulateService {

}

