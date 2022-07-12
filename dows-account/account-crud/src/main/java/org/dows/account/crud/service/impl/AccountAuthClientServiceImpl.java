package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountAuthClient;
import org.dows.account.crud.mapper.AccountAuthClientMapper;
import org.dows.account.crud.service.AccountAuthClientService;
import org.springframework.stereotype.Service;

/**
 * 账号-授权客户端(AccountAuthClient)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:21
 */
@Service
public class AccountAuthClientServiceImpl extends ServiceImpl<AccountAuthClientMapper, AccountAuthClient> implements AccountAuthClientService {

}
