package org.dows.account.crud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.pojo.form.GroupListQuery;
import org.dows.account.pojo.form.GroupUserQuery;
import org.dows.account.pojo.vo.AccountVo;

/**
 * 账号-组织架构维度信息(AccountOrganization)表服务接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:31
 */
public interface AccountOrganizationService extends IService<AccountOrganization> {
    IPage<AccountVo> queryAccountByGroup(IPage<AccountVo> page,
                                         GroupUserQuery groupUserQuery);

    IPage<GroupVo> queryGroupList(Page<GroupVo> pageInfo, GroupListQuery groupListQuery);
}
