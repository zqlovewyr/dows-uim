package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.UserExtinfo;
import org.dows.user.mapper.UserExtinfoMapper;
import org.dows.user.service.UserExtinfoService;
import org.springframework.stereotype.Service;


/**
 * 用户-扩展信息(UserExtinfo)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:16
 */
@Service("userExtinfoService")
public class UserExtinfoServiceImpl extends MybatisCrudServiceImpl<UserExtinfoMapper, UserExtinfo> implements UserExtinfoService {

}

