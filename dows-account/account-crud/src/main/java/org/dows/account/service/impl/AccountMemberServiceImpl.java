package org.dows.account.service.impl;

import org.dows.account.service.AccountMemberService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.entity.AccountMember;
import org.dows.account.mapper.AccountMemberMapper;
import org.springframework.stereotype.Service;


/**
 * 账号-会员(AccountMember)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:27
 */
@Service("accountMemberService")
public class AccountMemberServiceImpl extends MybatisCrudServiceImpl<AccountMemberMapper, AccountMember> implements AccountMemberService {

}

