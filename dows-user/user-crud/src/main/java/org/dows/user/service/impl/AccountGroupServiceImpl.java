package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountGroupMapper;
import org.dows.user.entity.AccountGroup;
import org.dows.user.service.AccountGroupService;
import org.springframework.stereotype.Service;


/**
 * 账号-组(AccountGroup)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@Service("accountGroupService")
public class AccountGroupServiceImpl extends MybatisCrudServiceImpl<AccountGroupMapper, AccountGroup> implements AccountGroupService {

}

