package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountGroupInfoMapper;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.service.AccountGroupInfoService;
import org.springframework.stereotype.Service;


/**
 * 账号-组(AccountGroupInfo)表服务实现类
 *
 * @author lait
 * @since 2023-01-09 15:01:25
 */
//@Service("accountGroupInfoService")
@DS("uim")
@Service
public class AccountGroupInfoServiceImpl extends MybatisCrudServiceImpl<AccountGroupInfoMapper, AccountGroupInfo> implements AccountGroupInfoService {

}

