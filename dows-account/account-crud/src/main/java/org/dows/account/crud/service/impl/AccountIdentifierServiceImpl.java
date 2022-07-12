package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountIdentifier;
import org.dows.account.crud.mapper.AccountIdentifierMapper;
import org.dows.account.crud.service.AccountIdentifierService;
import org.springframework.stereotype.Service;

/**
 * 账号-标识(AccountIdentifier)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:24
 */
@Service
public class AccountIdentifierServiceImpl extends ServiceImpl<AccountIdentifierMapper, AccountIdentifier> implements AccountIdentifierService {


    @Override
    public int updateAccountIdentifier(AccountIdentifier accountIdentifier) {
        return this.baseMapper.updateAccountIdentifier(accountIdentifier);
    }
}
