package org.dows.rbac.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dows.framework.api.Response;
import org.dows.rbac.PageBean;
import org.dows.rbac.SearchRequest;
import org.dows.rbac.api.RbacRoleApi;
import org.dows.rbac.biz.util.RsRbacRoleUtil;
import org.dows.rbac.dto.RbacRoleDTO;
import org.dows.rbac.entity.RbacRole;
import org.dows.rbac.service.RbacRoleService;
import org.dows.rbac.vo.RbacRoleSearchVO;
import org.dows.rbac.vo.RbacRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RBAC-角色(RbacRole)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:19
 */
@RequiredArgsConstructor
@Service
@DS("uim")
public class RbacRoleBiz implements RbacRoleApi {
    private final RbacRoleService rbacRoleService;

    @Override
    public Response<RbacRoleVo> getById(String id) {
        return Response.ok(RsRbacRoleUtil.rbacRole2VO(
                rbacRoleService.lambdaQuery()
                        .eq(RbacRole::getId, id)
                        .one())
        );
    }

    @Override
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
        queryWrapper.in(rbacRoleDTO.getIds() != null, RbacRole::getId, rbacRoleDTO.getIds())
                .eq(rbacRoleDTO.getId() != null, RbacRole::getId, rbacRoleDTO.getId())
                .like(rbacRoleDTO.getRolePid() != null, RbacRole::getPid, rbacRoleDTO.getRolePid())
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
        //复制属性
        List<RbacRoleVo> voList = new ArrayList<>();
        List<RbacRole> roleList = rolePage.getRecords();
        roleList.forEach(role->{
            RbacRoleVo vo = new RbacRoleVo();
            BeanUtils.copyProperties(role,vo);
            voList.add(vo);
        });
        BeanUtils.copyProperties(rolePage, pageVo,new String[]{"records"});
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    public Response<RbacRoleVo> getRbacRoleById(long id) {
        RbacRole rbacRole = rbacRoleService.getById(id);
        //复制属性
        RbacRoleVo vo = new RbacRoleVo();
        BeanUtils.copyProperties(rbacRole, vo);
        return Response.ok(vo);
    }

    @Override
    public Response<Boolean> updateRbacRoleById(RbacRoleDTO rbacRoleDTO) {
        RbacRole role = new RbacRole();
        BeanUtils.copyProperties(rbacRoleDTO, role);
        return Response.ok(rbacRoleService.updateById(role));
    }

    /**
     * 根据条件过滤数据，条件参数都为null则返回全部数据
     *
     * <p>若需要排序，可以在获取到数据后使用java来进行集合排序</p>
     *
     * @return
     * @author zhuchenmin
     */
    public Response<PageBean<RbacRoleVo>> filterData(RbacRoleSearchVO searchVO, Long pageNo, Long pageSize) {
        Set<String> ids = searchVO.getIds();
        String appId = searchVO.getAppId();
        // 如果文本内容不为空就转换为大写与数据库中字段值进行like比对
        String nameLetters = (null == searchVO.getNameLetters() ? null : searchVO.getNameLetters().toUpperCase());
        Long pid = searchVO.getPid();
        Boolean inherit = searchVO.getInherit();
        String roleCode = searchVO.getRoleCode();
        String roleName = searchVO.getRoleName();
        Integer status = searchVO.getStatus();
        pageNo = (pageNo == null || pageNo.equals(0L)) ? SearchRequest.PAGE_NO : pageNo;
        pageSize = (pageSize == null || pageSize.equals(0L)) ? SearchRequest.PAGE_SIZE : pageSize;

        LambdaQueryWrapper<RbacRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(null != ids && !ids.isEmpty(), RbacRole::getId, ids)
            .in(null != pid, RbacRole::getPid, pid)
            .in(null != inherit, RbacRole::getInherit, inherit)
            .eq(null != appId && !appId.isEmpty(), RbacRole::getAppId, appId)
            .like(null != nameLetters && !nameLetters.isEmpty(), RbacRole::getNameLetters, nameLetters)
            .eq(null != roleCode && !roleCode.isEmpty(), RbacRole::getRoleCode, roleCode)
            .eq(null != status, RbacRole::getStatus, status)
            .like(null != roleName && !roleName.isEmpty(), RbacRole::getRoleName, roleName);
        Page<RbacRole> page = rbacRoleService.page(new Page<>(pageNo, pageSize), wrapper);
        List<RbacRoleVo> rbacRoleVos = RsRbacRoleUtil.buildVOs(page.getRecords());
        return Response.ok(new PageBean<>(pageNo, pageSize, page.getPages(), page.getTotal(), rbacRoleVos));
    }

}
