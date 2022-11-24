package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacMetaMapper;
import org.dows.rbac.entity.RbacMeta;
import org.dows.rbac.service.RbacMetaService;
import org.springframework.stereotype.Service;


/**
 * rbac-元数据(RbacMeta)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:46
 */
@Service("rbacMetaService")
public class RbacMetaServiceImpl extends MybatisCrudServiceImpl<RbacMetaMapper, RbacMeta> implements RbacMetaService {

}

