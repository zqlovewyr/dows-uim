package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountIdentifierMapper;
import org.dows.account.entity.AccountIdentifier;
import org.dows.account.service.AccountIdentifierService;
import org.springframework.stereotype.Service;


/**
 * 账号标识(AccountIdentifier)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@DS("uim")
@Service("accountIdentifierService")
public class AccountIdentifierServiceImpl extends MybatisCrudServiceImpl<AccountIdentifierMapper, AccountIdentifier> implements AccountIdentifierService {

}

