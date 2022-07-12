package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dows.account.crud.entity.AccountGroup;
import org.dows.account.crud.entity.AccountOrganization;
import org.dows.account.crud.entity.PrincipalRole;
import org.dows.account.crud.service.AccountGroupService;
import org.dows.account.crud.service.AccountOrganizationService;
import org.dows.account.crud.service.PrincipalRoleService;
import org.dows.account.pojo.form.*;
import org.dows.account.pojo.vo.AccountVo;
import org.dows.auth.api.util.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 账号-组织架构维度信息(AccountOrganization)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 11:53:35
 */
@RequiredArgsConstructor
@Service
public class AccountOrganizationBiz {
    private final AccountOrganizationService accountOrganizationService;

    private final TenantInstanceService tenantInstanceService;

    private final PrincipalRoleService principalRoleService;

    private final AccountGroupService accountGroupService;

    public IPage<GroupVo> queryGroupList(GroupListQuery groupListQuery) {
        Page<GroupVo> pageInfo = new Page<>(groupListQuery.getPage(), groupListQuery.getSize());

        IPage<GroupVo> pageResult = accountOrganizationService.queryGroupList(pageInfo, groupListQuery);
        //Map<Long, TenantInstance> tenantInstanceMap = new HashMap<>();
        Map<Long, CountDTO> countDTOMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
            //List<Long> tenantIdList = pageResult.getRecords().stream().map(AccountOrganization::getTenantId).collect(Collectors.toList());
            //List<TenantInstance> tenantInstanceList = tenantInstanceService.list(new QueryWrapper<TenantInstance>().in("id", tenantIdList));
            //tenantInstanceMap = tenantInstanceList.stream().collect(Collectors.toMap(TenantInstance::getId, Function.identity()));
            List<Long> groupIdList = pageResult.getRecords().stream().map(GroupVo::getId).collect(Collectors.toList());
            List<CountDTO> countDTOList = accountGroupService.countByGroup(groupIdList);
            countDTOMap = countDTOList.stream().collect(Collectors.toMap(CountDTO::getId, Function.identity()));
        }
        Map<Long, CountDTO> finalCountDTOMap = countDTOMap;
        pageResult.getRecords().forEach(item -> {
            item.setUserCount(0L);
            if (finalCountDTOMap.containsKey(item.getId())) {
                item.setUserCount(finalCountDTOMap.get(item.getId()).getNum());
            }
        });
        return pageResult;
    }

    public Map<Long, CountDTO> countDTOMap(List<Long> groupIdList) {
        if (CollectionUtils.isEmpty(groupIdList)) {
            return new HashMap<>();
        }
        List<CountDTO> countDTOList = accountGroupService.countByGroup(groupIdList);
        return countDTOList.stream().collect(Collectors.toMap(CountDTO::getId, Function.identity()));
    }

    private GroupVo convertGroup(GroupVo item, Map<Long, CountDTO> countDTOMap) {
        GroupVo groupVo = new GroupVo();
        groupVo.setUserCount(0L);
        if (countDTOMap.containsKey(item.getId())) {
            groupVo.setUserCount(countDTOMap.get(item.getId()).getNum());
        }
        BeanUtils.copyProperties(item, groupVo);
        return groupVo;
    }

    public String updateGroup(GroupUpdate groupUpdate) {
        AccountOrganization accountOrganization = new AccountOrganization();
        accountOrganization.setOrgName(groupUpdate.getGroupName());
        accountOrganization.setId(groupUpdate.getId());
        accountOrganization.setDt(new Date());
        accountOrganization.setOrgTyp(0);
        accountOrganization.setTenantId(groupUpdate.getTenantId());
        accountOrganization.setDescr(groupUpdate.getDescr());
        boolean result = accountOrganizationService.saveOrUpdate(accountOrganization);
        return Constants.toResult(result);
    }

    public String delAccountGroup(List<Long> ids) {
        return Constants.toResult(accountOrganizationService.update(
                new UpdateWrapper<AccountOrganization>().set("deleted", 1).in("id", ids)));
    }

    public String batchUpdateStatus(BatchUpdateStatusForm reqBatchUpdateStatus) {
        return Constants.toResult(accountOrganizationService.update(new UpdateWrapper<AccountOrganization>()
                .set("status", reqBatchUpdateStatus.getStatus().getValue()).in("id", reqBatchUpdateStatus.getIds())));
    }

    public IPage<AccountVo> queryAccountGroup(GroupUserQuery groupUserQuery) {
        Page<AccountVo> pageInfo = new Page<>(groupUserQuery.getPage(), groupUserQuery.getSize());
        Long tenantId = UserUtil.getTenantId();
        groupUserQuery.setTenantId(tenantId);
        return accountOrganizationService.queryAccountByGroup(pageInfo, groupUserQuery);
    }

    public String platRoleSet(GroupRoleRelReq groupRoleRelReq) {
        //全部删除
        Long orgId = groupRoleRelReq.getOrgId();
        principalRoleService.lambdaUpdate()
                //.eq(PrincipalRole::getDeleted, 1)
                .eq(PrincipalRole::getPrincipalId, orgId)
                .eq(PrincipalRole::getPrincipalTyp, 1)
                .set(PrincipalRole::getDeleted, 1)
                .update();
//        principalRoleService.update(new UpdateWrapper<PrincipalRole>().set("deleted", 1).eq("principal_id", orgId)
//                .eq("principal_typ", 1));

        List<Long> roleIdList = groupRoleRelReq.getRoleIdList();
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Constants.FAIL;
        }
        List<PrincipalRole> principalRoles = new ArrayList<>();
        groupRoleRelReq.getRoleIdList().forEach(roleId -> {
            PrincipalRole principalRole = new PrincipalRole();
            principalRole.setRoleId(roleId);
            principalRole.setPrincipalId(orgId);
            principalRole.setPrincipalTyp(1);
            principalRoles.add(principalRole);

        });
        principalRoleService.updatePrincipalRole(principalRoles);
        return Constants.SUCCESS;
    }

    public List<Long> groupRoleList(Long orgId) {
        List<PrincipalRole> principalRoleList = principalRoleService.list(new QueryWrapper<PrincipalRole>().eq("principal_typ", 1)
                .eq("principal_id", orgId).eq("deleted", 0));
        return principalRoleList.stream().map(PrincipalRole::getRoleId).collect(Collectors.toList());
    }


    public String groupAddAccount(GroupAddAccountReq groupAddAccountReq) {
        //先全部删除
        accountGroupService.update(new UpdateWrapper<AccountGroup>().lambda().set(AccountGroup::getDeleted, 1)
                .eq(AccountGroup::getOrgId, groupAddAccountReq.getOrgId()));
        List<AccountGroup> accountGroupList = new ArrayList<>();
        groupAddAccountReq.getAccountIdList().forEach(accountId -> {
            AccountGroup accountGroup = new AccountGroup();
            accountGroup.setOrgId(groupAddAccountReq.getOrgId());
            accountGroup.setAccountId(accountId);
            accountGroup.setDeleted(Boolean.TRUE);
            accountGroupList.add(accountGroup);
        });
        accountGroupService.groupAddAccount(accountGroupList);
        return Constants.SUCCESS;
    }
}
