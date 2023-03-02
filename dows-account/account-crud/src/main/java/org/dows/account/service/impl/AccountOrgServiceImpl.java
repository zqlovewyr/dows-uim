package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountOrgMapper;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountOrgService;
import org.springframework.stereotype.Service;


/**
 * 账号-组织架构(AccountOrg)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@DS("uim")
@Service("accountOrgService")
public class AccountOrgServiceImpl extends MybatisCrudServiceImpl<AccountOrgMapper, AccountOrg> implements AccountOrgService {

}

