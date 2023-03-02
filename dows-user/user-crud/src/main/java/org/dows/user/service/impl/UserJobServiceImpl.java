package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.UserJob;
import org.dows.user.mapper.UserJobMapper;
import org.dows.user.service.UserJobService;
import org.springframework.stereotype.Service;


/**
 * 用户-工作信息(UserJob)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:18
 */
@DS("uim")
@Service("userJobService")
public class UserJobServiceImpl extends MybatisCrudServiceImpl<UserJobMapper, UserJob> implements UserJobService {

}

