package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountGroupMapper;
import org.dows.account.entity.AccountGroup;
import org.dows.account.service.AccountGroupService;
import org.springframework.stereotype.Service;


/**
 * 账号-组(AccountGroup)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@DS("uim")
@Service("accountGroupService")
public class AccountGroupServiceImpl extends MybatisCrudServiceImpl<AccountGroupMapper, AccountGroup> implements AccountGroupService {

}

