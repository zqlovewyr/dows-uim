package org.dows.account.service.impl;

import org.dows.account.service.AccountRoleService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.entity.AccountRole;
import org.dows.account.mapper.AccountRoleMapper;
import org.springframework.stereotype.Service;


/**
 * 账号-角色(AccountRole)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@Service("accountRoleService")
public class AccountRoleServiceImpl extends MybatisCrudServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {

}

