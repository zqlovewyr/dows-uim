package org.dows.rbac.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.dows.rbac.crud.entity.RbacResource;

/**
 * RBAC-应用资源(RbacResource)
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:59:08
 */
@Mapper
public interface RbacResourceMapper extends BaseMapper<RbacResource> {

}
