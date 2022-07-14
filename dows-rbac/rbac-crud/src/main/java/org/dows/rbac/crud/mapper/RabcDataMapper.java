package org.dows.rbac.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.rbac.crud.entity.RabcData;

/**
 * RBAC-数据资源(RabcData)
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:02
 */
@Mapper
public interface RabcDataMapper extends MybatisCrudMapper<RabcData> {

}
