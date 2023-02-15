package org.dows.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.account.entity.IffSetting;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;

/**
 * 规则设置
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Mapper
public interface IffSettingMapper extends MybatisCrudMapper<IffSetting> {

}

