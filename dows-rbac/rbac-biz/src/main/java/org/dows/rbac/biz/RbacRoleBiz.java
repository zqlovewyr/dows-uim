package org.dows.rbac.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.Response;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.dto.RbacRoleDTO;
import org.dows.rbac.vo.RbacRoleVo;
import org.dows.rbac.biz.util.RsRbacRoleUtil;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RBAC-角色(RbacRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
public class RbacRoleBiz implements RbacRoleApi {
    private final RbacRoleService rbacRoleService;

    public Response<RbacRoleVo> getById(String id) {
        return Response.ok(RsRbacRoleUtil.rbacRole2VO(
                rbacRoleService.lambdaQuery()
                        .eq(RbacRole::getId, id)
                        .one())
        );
    }

    public Response<List<RbacRoleVo>> getByIdList(List<String> idList) {
        return Response.ok(rbacRoleService.lambdaQuery()
                .in(RbacRole::getId, idList)
                .list()
                .stream().map(RsRbacRoleUtil::rbacRole2VO).collect(Collectors.toList()));
    }

    @Override
    public Response<List<RbacRoleVo>> getByIdListAndAppId(List<String> rbacRoleIdList, String appid) {
        return null;
    }

    public Response<List<RbacRoleVo>> getByIdList(List<String> idList, String appId) {
        return Response.ok(rbacRoleService.lambdaQuery()
                .in(RbacRole::getId, idList)
                .eq(RbacRole::getAppId, appId)
                .list()
                .stream().map(RsRbacRoleUtil::rbacRole2VO).collect(Collectors.toList()));
    }

    @Override
    public Response<IPage<RbacRoleVo>> customRbacRoleList(RbacRoleDTO rbacRoleDTO) {
        LambdaQueryWrapper<RbacRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(rbacRoleDTO.getRolePid() != null, RbacRole::getPid, rbacRoleDTO.getRolePid())
                .like(StringUtils.isNotEmpty(rbacRoleDTO.getRoleName()), RbacRole::getRoleName, rbacRoleDTO.getRoleName())
                .eq(StringUtils.isNotEmpty(rbacRoleDTO.getNameLetter()), RbacRole::getNameLetters, rbacRoleDTO.getNameLetter())
                .like(StringUtils.isNotEmpty(rbacRoleDTO.getRoleCode()), RbacRole::getRoleCode, rbacRoleDTO.getRoleCode())
                .eq(StringUtils.isNotEmpty(rbacRoleDTO.getRoleIcon()), RbacRole::getRoleIcon, rbacRoleDTO.getRoleIcon())
                .eq(rbacRoleDTO.getStatus() != null, RbacRole::getStatus, rbacRoleDTO.getStatus())
                .like(StringUtils.isNotEmpty(rbacRoleDTO.getDescr()), RbacRole::getDescr, rbacRoleDTO.getDescr())
                .like(StringUtils.isNotEmpty(rbacRoleDTO.getAppId()),RbacRole::getAppId,rbacRoleDTO.getAppId())
                .like(rbacRoleDTO.getTenantId() != null,RbacRole::getTenantId,rbacRoleDTO.getTenantId())
                .eq(rbacRoleDTO.getDt() != null, RbacRole::getDt, rbacRoleDTO.getDt())
                .gt(rbacRoleDTO.getStartTime() != null, RbacRole::getDt, rbacRoleDTO.getStartTime())
                .lt(rbacRoleDTO.getEndTime() != null, RbacRole::getDt, rbacRoleDTO.getEndTime())
                .orderByDesc(RbacRole::getDt);
        Page<RbacRole> page = new Page<>(rbacRoleDTO.getPageNo(), rbacRoleDTO.getPageSize());
        IPage<RbacRole> rolePage = rbacRoleService.page(page, queryWrapper);
        IPage<RbacRoleVo> pageVo = new Page<>();
        BeanUtils.copyProperties(rolePage, pageVo);
        return Response.ok(pageVo);
    }

}
