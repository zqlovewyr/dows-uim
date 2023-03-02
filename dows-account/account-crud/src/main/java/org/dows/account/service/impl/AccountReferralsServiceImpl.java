package org.dows.account.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.account.mapper.AccountReferralsMapper;
import org.dows.account.entity.AccountReferrals;
import org.dows.account.service.AccountReferralsService;
import org.springframework.stereotype.Service;


/**
 * 推荐关系(AccountReferrals)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@DS("uim")
@Service("accountReferralsService")
public class AccountReferralsServiceImpl extends MybatisCrudServiceImpl<AccountReferralsMapper, AccountReferrals> implements AccountReferralsService {

}

