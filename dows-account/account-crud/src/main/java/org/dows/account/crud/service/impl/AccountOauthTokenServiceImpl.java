package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountOauthToken;
import org.dows.account.crud.mapper.AccountOauthTokenMapper;
import org.dows.account.crud.service.AccountOauthTokenService;
import org.springframework.stereotype.Service;

/**
 * 账号-第三方账号授权(AccountOauthToken)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:29
 */
@Service
public class AccountOauthTokenServiceImpl extends ServiceImpl<AccountOauthTokenMapper, AccountOauthToken> implements AccountOauthTokenService {

}
