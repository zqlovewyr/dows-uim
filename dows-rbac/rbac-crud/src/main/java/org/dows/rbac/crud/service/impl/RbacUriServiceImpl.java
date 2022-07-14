package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RbacUri;
import org.dows.rbac.crud.mapper.RbacUriMapper;
import org.dows.rbac.crud.service.RbacUriService;
import org.springframework.stereotype.Service;

/**
 * RBAC-功能资源(RbacUri)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:49
 */
@Service
public class RbacUriServiceImpl extends MybatisCrudServiceImpl<RbacUriMapper, RbacUri> implements RbacUriService {

}
