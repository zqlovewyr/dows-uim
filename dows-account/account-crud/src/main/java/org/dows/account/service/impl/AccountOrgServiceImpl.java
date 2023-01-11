package org.dows.account.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.dows.account.entity.AccountOrg;
import org.dows.account.mapper.AccountOrgMapper;
import org.dows.account.query.AccountOrgQuery;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;


/**
 * 账号-组织架构(AccountOrg)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Service("accountOrgService")
@RequiredArgsConstructor
public class AccountOrgServiceImpl extends MybatisCrudServiceImpl<AccountOrgMapper, AccountOrg> implements AccountOrgService {


    private final AccountOrgMapper accountOrgMapper;
    @Override
    public IPage<AccountOrgVo> getListByPage(IPage<AccountOrgVo> page, AccountOrgQuery accountOrgQuery) {
        IPage<AccountOrgVo> list = accountOrgMapper.selectAccountOrgPage(page, accountOrgQuery);
        return list;
    }
}

