package org.dows.account.service.impl;

import org.dows.account.entity.IffSetting;
import org.dows.account.mapper.IffSettingMapper;
import org.dows.account.service.IffSettingService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 规则配置表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Service("iffSettingService")
public class IffSettingServiceImpl extends MybatisCrudServiceImpl<IffSettingMapper, IffSetting> implements IffSettingService {

}

