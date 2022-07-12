package org.dows.account.crud.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.AccountGroup;

import java.util.List;

/**
 * 账号-账号组维度信息(AccountGroup)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
public interface AccountGroupService extends IService<AccountGroup> {
    void groupAddAccount(List<AccountGroup> accountGroupList);

    IPage<GroupVo> accountGroupList(Page<TenantVo> pageInfo, QueryWrapper<AccountGroup> eq);

    List<CountDTO> countByGroup(List<Long> groupIdList);

}
