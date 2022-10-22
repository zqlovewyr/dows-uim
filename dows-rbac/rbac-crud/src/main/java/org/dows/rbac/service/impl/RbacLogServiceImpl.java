package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacLogMapper;
import org.dows.rbac.entity.RbacLog;
import org.dows.rbac.service.RbacLogService;
import org.springframework.stereotype.Service;


/**
 * rbac-日志(RbacLog)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:16
 */
@Service("rbacLogService")
public class RbacLogServiceImpl extends MybatisCrudServiceImpl<RbacLogMapper, RbacLog> implements RbacLogService {

}

