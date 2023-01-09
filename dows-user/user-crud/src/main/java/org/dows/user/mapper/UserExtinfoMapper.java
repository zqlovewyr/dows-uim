package org.dows.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.user.entity.UserExtinfo;

/**
 * 用户-扩展信息(UserExtinfo)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:16
 */
@Mapper
public interface UserExtinfoMapper extends MybatisCrudMapper<UserExtinfo> {

}

