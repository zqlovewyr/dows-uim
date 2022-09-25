package org.dows.account.service.impl;


import org.dows.account.entity.AccountUser;
import org.dows.account.mapper.AccountUserMapper;
import org.dows.account.service.AccountUserService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-用户维度信息(AccountUser)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:53
 */
@Service
public class AccountUserServiceImpl extends MybatisCrudServiceImpl<AccountUserMapper, AccountUser> implements AccountUserService {

}
