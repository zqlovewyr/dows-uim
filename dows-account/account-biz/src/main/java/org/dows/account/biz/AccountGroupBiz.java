package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupApi;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountOrg;
import org.dows.account.entity.AccountRole;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountGroupBiz implements AccountGroupApi {

    private final AccountGroupService accountGroupService;

    private final AccountGroupInfoService accountGroupInfoService;

    private final AccountOrgService accountOrgService;

    private final AccountRoleService accountRoleService;

    /**
     * 根据组织ids 查询对应角色
     * roleId 若为空则查询所有用户
     *
     * @param accountOrgIds 组织IDS
     * @param roleCode      指定rbacRoleCode
     * @return 返回每个组织下对应rbacRoleCode的AccountGroupList
     */
    public Map<String, List<AccountGroup>> mapAccountGroupByRole(List<String> accountOrgIds, String roleCode) {
        if (CollectionUtils.isEmpty(accountOrgIds)) {
            return Collections.emptyMap();
        }
        List<AccountGroup> accountGroupList = accountGroupService.lambdaQuery()
                .in(AccountGroup::getOrgId, accountOrgIds)
                .eq(AccountGroup::getDeleted, false)
                .list();
        if (StringUtils.isNotBlank(roleCode)) {
            // list accountRole by roleId
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleCode, roleCode)
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .list();
            if (CollectionUtils.isEmpty(accountRoleList)) {
                return Collections.emptyMap();
            }
            List<AccountGroup> collect = accountGroupList.stream()
                    .filter(item ->
                            accountRoleList.stream()
                                    .map(AccountRole::getPrincipalId)
                                    .collect(Collectors.toList()).contains(item.getAccountId())
                    ).collect(Collectors.toList());
            return collect.stream().collect(Collectors.groupingBy(AccountGroup::getOrgId, HashMap::new, Collectors.toList()));
        }
        return accountGroupList.stream().collect(Collectors.groupingBy(AccountGroup::getOrgId, HashMap::new, Collectors.toList()));
    }

    /**
     * batch insert account-group
     * 批量创建 账号-组
     *
     * @param accountOrgGroups account-groups
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertGroup(List<AccountOrgGroupDTO> accountOrgGroups) {
        if (CollectionUtils.isEmpty(accountOrgGroups)) {
            return;
        }
        List<AccountGroup> accountGroups = new ArrayList<>();
        accountOrgGroups.forEach(item -> {
            AccountGroup accountGroup = new AccountGroup();
            AccountUtil.validateAccountGroupDTO(item);
            BeanUtils.copyProperties(item, accountGroup);
            accountGroups.add(accountGroup);
        });
        accountGroupService.saveBatch(accountGroups, accountGroups.size());
    }

    /**
     * 自定义账号-组 列表
     *
     * @param accountGroupDTO
     * @return Response<IPage < AccountGroupVo>>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDTO) {
        LambdaQueryWrapper<AccountGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountGroupDTO.getAppId()), AccountGroup::getAppId, accountGroupDTO.getAppId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getAccountId()), AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .eq(accountGroupDTO.getId() != null, AccountGroup::getId, accountGroupDTO.getId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .eq(AccountGroup::getDeleted, false)
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(accountGroupDTO.getPageNo(), accountGroupDTO.getPageSize());
        IPage<AccountGroup> groupList = accountGroupService.page(page, queryWrapper);
        //复制属性
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupList, pageVo);
        return Response.ok(pageVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> insertAccountGroup(AccountOrgGroupDTO accountOrgGroupDTO) {
        boolean flag = true;
        //1、插入组织架构表
        AccountOrg accountOrg = new AccountOrg();
        //1.1、设置组织架构属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
        /*accountOrg.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));*/
        accountOrg.setDescr(accountOrgGroupDTO.getOrgDescr());
        accountOrg.setSorted(accountOrgGroupDTO.getOrgSorted().toString());
        accountOrg.setStatus(accountOrgGroupDTO.getOrgStatus().toString());
        accountOrg.setDt(accountOrgGroupDTO.getOrgDt());
        boolean flagOrg = accountOrgService.save(accountOrg);
        if (flagOrg == false) {
            flag = false;
        }
        //2、插入组-实例表
        AccountGroupInfo accountGroupInfo = new AccountGroupInfo();
        //2.1、设置组实例属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountGroupInfo);
        boolean flagInfo = accountGroupInfoService.save(accountGroupInfo);
        if (flagInfo == false) {
            flag = false;
        }
        return Response.ok(flag);
    }
}
