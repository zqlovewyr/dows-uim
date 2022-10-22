package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountIdentifierMapper;
import org.dows.user.entity.AccountIdentifier;
import org.dows.user.service.AccountIdentifierService;
import org.springframework.stereotype.Service;


/**
 * 账号标识(AccountIdentifier)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@Service("accountIdentifierService")
public class AccountIdentifierServiceImpl extends MybatisCrudServiceImpl<AccountIdentifierMapper, AccountIdentifier> implements AccountIdentifierService {

}

