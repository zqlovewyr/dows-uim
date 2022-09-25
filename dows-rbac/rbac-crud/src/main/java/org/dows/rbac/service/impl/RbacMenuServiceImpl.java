package org.dows.rbac.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RbacMenu;
import org.dows.rbac.mapper.RbacMenuMapper;
import org.dows.rbac.service.RbacMenuService;
import org.springframework.stereotype.Service;

/**
 * RBAC-菜单资源(RbacMenu)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:47
 */
@Service
public class RbacMenuServiceImpl extends MybatisCrudServiceImpl<RbacMenuMapper, RbacMenu> implements RbacMenuService {

}
