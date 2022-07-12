package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.api.dto.UserContactDto;
import org.dows.account.crud.entity.AccountUser;
import org.dows.account.crud.mapper.AccountUserMapper;
import org.dows.account.crud.service.AccountUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号-用户维度信息(AccountUser)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:37
 */
@Service
public class AccountUserServiceImpl extends ServiceImpl<AccountUserMapper, AccountUser> implements AccountUserService {

    @Override
    public List<UserContactDto> queryUserContacts(QueryWrapper<AccountUser> wrapper) {
        return this.baseMapper.queryUserContacts(wrapper);
    }
}
