package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountGroup;
import org.dows.account.crud.mapper.AccountGroupMapper;
import org.dows.account.crud.service.AccountGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
@Service
public class AccountGroupServiceImpl extends ServiceImpl<AccountGroupMapper, AccountGroup> implements AccountGroupService {
    @Override
    public void groupAddAccount(List<AccountGroup> accountGroupList) {
        accountGroupList.forEach(accountGroup -> {
            this.baseMapper.groupAddAccount(accountGroup);
        });
    }

    @Override
    public IPage<GroupVo> accountGroupList(Page<TenantVo> pageInfo, QueryWrapper<AccountGroup> wrapper) {
        return this.baseMapper.accountGroupList(pageInfo, wrapper);
    }

    @Override
    public List<CountDTO> countByGroup(List<Long> groupIdList) {
        return this.baseMapper.countByGroup(groupIdList);
    }
}
