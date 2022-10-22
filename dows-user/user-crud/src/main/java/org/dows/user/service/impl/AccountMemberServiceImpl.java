package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountMemberMapper;
import org.dows.user.entity.AccountMember;
import org.dows.user.service.AccountMemberService;
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

