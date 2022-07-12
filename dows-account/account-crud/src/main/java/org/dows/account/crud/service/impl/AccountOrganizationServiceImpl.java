package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.crud.mapper.AccountOrganizationMapper;
import org.dows.account.crud.service.AccountOrganizationService;
import org.dows.account.pojo.form.GroupListQuery;
import org.dows.account.pojo.form.GroupUserQuery;
import org.dows.account.pojo.vo.AccountVo;
import org.springframework.stereotype.Service;


/**
 * 账号-组织架构维度信息(AccountOrganization)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:31
 */
@Service
public class AccountOrganizationServiceImpl extends ServiceImpl<AccountOrganizationMapper, AccountOrganization> implements AccountOrganizationService {
    @Override
    public IPage<AccountVo> queryAccountByGroup(IPage<AccountVo> page, GroupUserQuery groupUserQuery) {
        return this.baseMapper.queryAccountByGroup(page, groupUserQuery);
    }

    @Override
    public IPage<GroupVo> queryGroupList(Page<GroupVo> pageInfo, GroupListQuery groupListQuery) {
        return this.baseMapper.queryGroupList(pageInfo, groupListQuery);
    }
}
