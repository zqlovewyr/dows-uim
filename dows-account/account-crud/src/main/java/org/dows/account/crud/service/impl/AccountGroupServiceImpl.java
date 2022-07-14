package org.dows.account.crud.service.impl;


import org.dows.account.crud.entity.AccountGroup;
import org.dows.account.crud.mapper.AccountGroupMapper;
import org.dows.account.crud.service.AccountGroupService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-账号组维度信息(AccountGroup)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:18
 */
@Service
public class AccountGroupServiceImpl extends MybatisCrudServiceImpl<AccountGroupMapper, AccountGroup> implements AccountGroupService {

}
