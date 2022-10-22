package org.dows.account.service.impl;

import org.dows.account.service.AccountInstanceService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.entity.AccountInstance;
import org.dows.account.mapper.AccountInstanceMapper;
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

