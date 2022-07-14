package org.dows.account.crud.service.impl;


import org.dows.account.crud.entity.AccountIdentifier;
import org.dows.account.crud.mapper.AccountIdentifierMapper;
import org.dows.account.crud.service.AccountIdentifierService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 账号-标识(AccountIdentifier)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:22
 */
@Service
public class AccountIdentifierServiceImpl extends MybatisCrudServiceImpl<AccountIdentifierMapper, AccountIdentifier> implements AccountIdentifierService {

}
