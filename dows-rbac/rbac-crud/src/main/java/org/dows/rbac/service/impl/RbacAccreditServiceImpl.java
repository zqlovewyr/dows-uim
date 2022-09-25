package org.dows.rbac.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RbacAccredit;
import org.dows.rbac.mapper.RbacAccreditMapper;
import org.dows.rbac.service.RbacAccreditService;
import org.springframework.stereotype.Service;

/**
 * RBAC-角色授权(RbacAccredit)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:46
 */
@Service
public class RbacAccreditServiceImpl extends MybatisCrudServiceImpl<RbacAccreditMapper, RbacAccredit> implements RbacAccreditService {

}
