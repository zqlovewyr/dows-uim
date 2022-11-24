package org.dows.rbac.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacMenuMapper;
import org.dows.rbac.entity.RbacMenu;
import org.dows.rbac.service.RbacMenuService;
import org.springframework.stereotype.Service;


/**
 * rbac-菜单(RbacMenu)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:45
 */
@Service("rbacMenuService")
public class RbacMenuServiceImpl extends MybatisCrudServiceImpl<RbacMenuMapper, RbacMenu> implements RbacMenuService {

}

