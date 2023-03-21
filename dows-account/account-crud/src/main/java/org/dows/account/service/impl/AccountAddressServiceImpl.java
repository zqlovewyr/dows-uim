package org.dows.account.service.impl;

import org.dows.account.entity.AccountAddress;
import org.dows.account.entity.AccountUser;
import org.dows.account.mapper.AccountAddressMapper;
import org.dows.account.mapper.AccountUserMapper;
import org.dows.account.service.AccountAddressService;
import org.dows.account.service.AccountUserService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 账号-地址(实名认证后)(AccountAddress)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@Service("accountAddressService")
public class AccountAddressServiceImpl extends MybatisCrudServiceImpl<AccountAddressMapper, AccountAddress> implements AccountAddressService {

}

