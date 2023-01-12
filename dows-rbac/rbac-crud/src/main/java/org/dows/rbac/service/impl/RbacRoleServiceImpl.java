package org.dows.rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacRoleMapper;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.service.RbacRoleService;
import org.dows.rbac.vo.RbacRoleVo;
import org.springframework.stereotype.Service;


/**
 * rbac-角色(RbacRole)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:48
 */
@Service("rbacRoleService")
@RequiredArgsConstructor
public class RbacRoleServiceImpl extends MybatisCrudServiceImpl<RbacRoleMapper, RbacRole> implements RbacRoleService {

    private final RbacRoleMapper rbacRoleMapper;
    @Override
    public IPage<RbacRoleVo> getListByPage(IPage<RbacRoleVo> page, RbacRoleQuery rbacRoleQuery) {
        return rbacRoleMapper.selectRbacRolePage(page,rbacRoleQuery);
    }
}

