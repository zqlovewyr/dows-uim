package org.dows.account.crud.service.impl;


import org.dows.account.crud.entity.AccountRole;
import org.dows.account.crud.mapper.AccountRoleMapper;
import org.dows.account.crud.service.AccountRoleService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-主体角色维度信息(AccountRole)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:41
 */
@Service
public class AccountRoleServiceImpl extends MybatisCrudServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {

}
