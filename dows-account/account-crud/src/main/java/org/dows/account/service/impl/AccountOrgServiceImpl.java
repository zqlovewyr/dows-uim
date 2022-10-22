package org.dows.account.service.impl;

import org.dows.account.service.AccountOrgService;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.entity.AccountOrg;
import org.dows.account.mapper.AccountOrgMapper;
import org.springframework.stereotype.Service;


/**
 * 账号-组织架构(AccountOrg)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@Service("accountOrgService")
public class AccountOrgServiceImpl extends MybatisCrudServiceImpl<AccountOrgMapper, AccountOrg> implements AccountOrgService {

}

