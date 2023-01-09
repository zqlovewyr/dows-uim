package org.dows.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.dows.account.entity.AccountInstance;
import org.dows.account.mapper.AccountInstanceMapper;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 账号-实例(AccountInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Service("accountInstanceService")
@RequiredArgsConstructor
public class AccountInstanceServiceImpl extends MybatisCrudServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

    private final AccountInstanceMapper accountInstanceMapper;
    @Override
    public List<AccountInstanceResVo> getAccountInstanceByUserNameAndTenantId(Map<String, Object> param) {
        return accountInstanceMapper.getAccountInstanceByUserNameAndTenantId(param);
    }
}

