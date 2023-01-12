package org.dows.rbac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.framework.crud.mybatis.MybatisCrudService;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.vo.RbacRoleVo;


/**
 * rbac-角色(RbacRole)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:48
 */
public interface RbacRoleService extends MybatisCrudService<RbacRole> {

    /**
     * 分页查询组织架构
     * @param page
     * @param rbacRoleQuery
     * @return
     */
    IPage<RbacRoleVo> getListByPage(IPage<RbacRoleVo> page, RbacRoleQuery rbacRoleQuery);

}

