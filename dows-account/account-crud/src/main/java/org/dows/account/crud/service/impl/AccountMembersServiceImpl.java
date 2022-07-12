package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountMembers;
import org.dows.account.crud.mapper.AccountMembersMapper;
import org.dows.account.crud.service.AccountMembersService;
import org.springframework.stereotype.Service;

/**
 * 账号-会员维度信息(AccountMembers)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:28
 */
@Service
public class AccountMembersServiceImpl extends ServiceImpl<AccountMembersMapper, AccountMembers> implements AccountMembersService {

}
