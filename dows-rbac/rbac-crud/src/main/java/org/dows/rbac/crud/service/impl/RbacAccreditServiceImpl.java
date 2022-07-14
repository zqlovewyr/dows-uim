package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RbacAccredit;
import org.dows.rbac.crud.mapper.RbacAccreditMapper;
import org.dows.rbac.crud.service.RbacAccreditService;
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
