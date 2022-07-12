package org.dows.account.rest.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.auth.api.util.UserUtil;
import org.dows.rbac.vo.GroupVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.AccountOrganizationBiz;
import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.crud.service.AccountOrganizationService;
import org.dows.account.pojo.IdsUpdate;
import org.dows.account.pojo.form.*;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.account.rest.admin.AccountOrganizationRest;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 账号-组织架构维度信息(AccountOrganization)表控制层
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:35
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountOrganizationController implements AccountOrganizationRest<AccountOrganization, AccountOrganizationService> {
    @Getter
    private final AccountOrganizationService service;
    private final AccountOrganizationBiz accountOrganizationBiz;

    @Override
    public Response<IPage<GroupVo>> queryGroupList(GroupListQuery groupListQuery) {
        if (UserUtil.isTenant()) {
            groupListQuery.setTenantId(UserUtil.getTenantId());
        }
        return Response.ok(accountOrganizationBiz.queryGroupList(groupListQuery));
    }


    @Override
    public Response<String> updateGroup(GroupUpdate groupUpdate) {
        if (UserUtil.isTenant()) {
            groupUpdate.setTenantId(UserUtil.getTenantId());
        }
        return Response.ok(accountOrganizationBiz.updateGroup(groupUpdate));
    }

    @Override
    public Response<String> delAccountGroup(IdsUpdate ids) {
        return Response.ok(accountOrganizationBiz.delAccountGroup(ids.getIds()));
    }

    @Override
    public Response<String> batchUpdateStatus(BatchUpdateStatusForm reqBatchUpdateStatus) {
        return Response.ok(accountOrganizationBiz.batchUpdateStatus(reqBatchUpdateStatus));
    }

    @Override
    public Response<IPage<AccountVo>> queryAccountGroup(GroupUserQuery groupUserQuery) {
        return Response.ok(accountOrganizationBiz.queryAccountGroup(groupUserQuery));
    }

    @Override
    public Response<String> platRoleSet(GroupRoleRelReq groupRoleRelReq) {
        return Response.ok(accountOrganizationBiz.platRoleSet(groupRoleRelReq));
    }


    @Override
    public Response<List<Long>> groupRoleList(Long orgId) {
        return Response.ok(accountOrganizationBiz.groupRoleList(orgId));
    }

    @Override
    public Response<String> groupAddAccount(GroupAddAccountReq groupAddAccountReq) {
        return Response.ok(accountOrganizationBiz.groupAddAccount(groupAddAccountReq));
    }


}
