package org.dows.rbac.crud.service.impl;


import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.crud.entity.RabcData;
import org.dows.rbac.crud.mapper.RabcDataMapper;
import org.dows.rbac.crud.service.RabcDataService;
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
