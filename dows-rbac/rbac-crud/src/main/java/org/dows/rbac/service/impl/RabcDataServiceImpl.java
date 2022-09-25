package org.dows.rbac.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RabcData;
import org.dows.rbac.mapper.RabcDataMapper;
import org.dows.rbac.service.RabcDataService;
import org.springframework.stereotype.Service;

/**
 * RBAC-数据资源(RabcData)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 23:01:46
 */
@Service
public class RabcDataServiceImpl extends MybatisCrudServiceImpl<RabcDataMapper, RabcData> implements RabcDataService {

}
