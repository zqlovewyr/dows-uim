package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountInstanceMapper;
import org.dows.user.entity.AccountInstance;
import org.dows.user.service.AccountInstanceService;
import org.springframework.stereotype.Service;


/**
 * 账号-实例(AccountInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@Service("accountInstanceService")
public class AccountInstanceServiceImpl extends MybatisCrudServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

}

