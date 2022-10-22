package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountRoleMapper;
import org.dows.user.entity.AccountRole;
import org.dows.user.service.AccountRoleService;
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

