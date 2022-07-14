package org.dows.rbac.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dows.rbac.crud.entity.RbacPermission;
import org.dows.rbac.pojo.form.PermTypeQuery;
import org.dows.rbac.pojo.query.PermListQuery;
import org.dows.rbac.pojo.vo.PermSimpleVo;
import org.dows.rbac.pojo.vo.PermVo;

import java.util.List;

/**
 * RBAC权限资源(RbacPermission)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:59:08
 */
@Mapper
public interface RbacPermissionMapper extends BaseMapper<RbacPermission> {

    @Select("@RbacPermissionMapper.queryPermList")
    IPage<PermVo> queryPermList(Page<PermVo> pageInfo, @Param("p") PermListQuery permListQuery);

    @Select("@RbacPermissionMapper.queryPermSimpleList")
    List<PermSimpleVo> queryPermSimpleList(@Param("p") PermTypeQuery permTypeQuery);

    @Select("@RbacPermissionMapper.queryCurrentPermByPlatform")
    List<PermDto> queryCurrentPermByPlatform(@Param("id") Long id);

    @Select("@RbacPermissionMapper.queryCurrentPermByTenant")
    List<PermDTO> queryCurrentPermByTenant(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);
}
