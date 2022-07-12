package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountTenant;
import org.dows.account.crud.mapper.AccountTenantMapper;
import org.dows.account.crud.service.AccountTenantService;
import org.dows.account.pojo.form.AccountTenantRelQuery;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账号-租户维度信息(AccountTenant)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
@Service
public class AccountTenantServiceImpl extends ServiceImpl<AccountTenantMapper, AccountTenant> implements AccountTenantService {
    @Override
    public IPage<AccountVo> queryAccountTenant(IPage<AccountVo> page, AccountTenantRelQuery accountTenantRelQuery) {
        return this.baseMapper.queryAccountTenant(page, accountTenantRelQuery);
    }

    @Override
    public IPage<TenantVo> queryAccountTenantList(IPage<TenantVo> page, AccountOwnerQuery accountOwnerQuery) {
        return this.baseMapper.queryAccountTenantList(page, accountOwnerQuery);
    }


    @Override
    public List<CountDTO> countAccountNum(List<Long> tenantIds) {
        return this.baseMapper.countAccountNum(tenantIds);
    }

    @Override
    public List<CountDTO> countGroupNum(List<Long> tenantIds) {
        return this.baseMapper.countGroupNum(tenantIds);
    }

    @Override
    public int updateAccountTenant(List<AccountTenant> accountTenantList) {
        accountTenantList.forEach(accountTenant -> {
            this.baseMapper.updateAccountTenant(accountTenant);
        });
        return 1;
    }
}
