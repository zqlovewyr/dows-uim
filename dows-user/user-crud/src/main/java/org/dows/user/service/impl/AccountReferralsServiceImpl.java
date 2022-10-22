package org.dows.user.service.impl;

import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.AccountReferralsMapper;
import org.dows.user.entity.AccountReferrals;
import org.dows.user.service.AccountReferralsService;
import org.springframework.stereotype.Service;


/**
 * 推荐关系(AccountReferrals)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@Service("accountReferralsService")
public class AccountReferralsServiceImpl extends MybatisCrudServiceImpl<AccountReferralsMapper, AccountReferrals> implements AccountReferralsService {

}

