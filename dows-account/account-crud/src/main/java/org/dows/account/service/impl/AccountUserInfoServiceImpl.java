package org.dows.account.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.dows.account.entity.AccountUserInfo;
import org.dows.account.mapper.AccountMapper;
import org.dows.account.mapper.AccountUserInfoMapper;
import org.dows.account.query.AccountQuery;
import org.dows.account.service.AccountUserInfoService;
import org.dows.account.vo.AccountVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

@Service("accountUserInfoService")
@RequiredArgsConstructor
public class AccountUserInfoServiceImpl extends MybatisCrudServiceImpl<AccountUserInfoMapper, AccountUserInfo> implements AccountUserInfoService {

    private final AccountMapper accountMapper;

    @Override
    public IPage<AccountVo> getListByPage(IPage<AccountVo> page, AccountQuery accountQuery) {
        return accountMapper.selectAccountPage(page, accountQuery);
    }
}
