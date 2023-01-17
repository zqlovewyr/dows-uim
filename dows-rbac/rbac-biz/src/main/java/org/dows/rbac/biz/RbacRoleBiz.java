package org.dows.rbac.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.Response;
import org.dows.rbac.api.dto.RbacRoleDto;
import org.dows.rbac.biz.exception.RbacException;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.query.RbacMenuQuery;
import org.dows.rbac.query.RbacRoleQuery;
import org.dows.rbac.service.RbacMenuService;
import org.dows.rbac.service.RbacRoleService;
import org.dows.rbac.vo.RbacMenuVo;
import org.dows.rbac.vo.RbacRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * RBAC-角色(RbacRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
public class RbacRoleBiz {

    private final RbacRoleService service;

    public Response getRbacRoleListPage(RbacRoleQuery query){

        Page<RbacRoleVo> page = new Page<>(query.getOffset(), query.getSize());
        IPage<RbacRoleVo> listByPage = service.getListByPage(page,query);
        return Response.ok(listByPage);
    }
    public Response getRbacRoleById(Long id){

        RbacRole rbacRole = service.lambdaQuery()
                .select(RbacRole::getId, RbacRole::getRoleCode, RbacRole::getRoleName,RbacRole::getDescr,RbacRole::getTenantId)
                .eq(RbacRole::getId, id)
                .oneOpt()
                .orElseThrow(() -> {
                    throw new RbacException("根据id查询角色不存在");
                });

        return Response.ok(rbacRole);
    }
    public Response deleteById(Long id){
        service.removeById(id);
        return Response.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public RbacRoleVo createOrUpdate(@RequestBody RbacRoleDto rbacRoleDto) {
        //step1: check static rule
        //step2: createOrg
        RbacRole rbacRole = new RbacRole();
        BeanUtils.copyProperties(rbacRoleDto, rbacRole);
        if(rbacRole.getId() == null){
            this.service.save(rbacRole);
        }else{
            this.service.lambdaUpdate()
                    .set(RbacRole::getRoleCode,rbacRole.getRoleCode())
                    .set(RbacRole::getRoleName,rbacRole.getRoleName())
                    .set(RbacRole::getRoleIcon,rbacRole.getRoleIcon())
                    .set(RbacRole::getDescr,rbacRole.getDescr())
                    .eq(RbacRole::getId,rbacRole.getId())
                    .update();
        }

        //step5: return VO
        RbacRoleVo rbacRoleVo = new RbacRoleVo();
        BeanUtils.copyProperties(rbacRole, rbacRoleVo);
        return rbacRoleVo;
    }

}
