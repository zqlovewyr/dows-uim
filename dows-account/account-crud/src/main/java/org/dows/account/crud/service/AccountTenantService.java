package org.dows.account.crud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.AccountTenant;
import org.dows.account.pojo.form.AccountTenantRelQuery;
import org.dows.account.pojo.query.AccountOwnerQuery;
import org.dows.account.pojo.vo.AccountVo;

import java.util.List;

/**
 * 账号-租户维度信息(AccountTenant)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
public interface AccountTenantService extends IService<AccountTenant> {
    IPage<AccountVo> queryAccountTenant(IPage<AccountVo> page,
                                        AccountTenantRelQuery accountTenantRelQuery);


    IPage<TenantVo> queryAccountTenantList(IPage<TenantVo> page,
                                           AccountOwnerQuery accountOwnerQuery);


    List<CountDTO> countAccountNum(List<Long> tenantIds);

    List<CountDTO> countGroupNum(List<Long> tenantIds);

    int updateAccountTenant(List<AccountTenant> accountTenantList);
}
