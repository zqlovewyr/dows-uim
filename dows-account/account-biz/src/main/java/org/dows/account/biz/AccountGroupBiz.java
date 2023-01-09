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
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountRole;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountGroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 查询 账号-组及负责人 列表
     *
     * @param accountGroupDTO
     * @param accountGroupInfoDTO
     * @param pageNo
     * @param pageSize
     */
    public IPage<AccountGroupVo> accountGroupUnionList(@RequestBody AccountGroupDTO accountGroupDTO, @RequestBody AccountGroupInfoDTO accountGroupInfoDTO, Integer pageNo, Integer pageSize) {
        //筛选对应对应团队负责人相关信息
        List<AccountGroupInfo> accountGroupInfoList = new ArrayList<>();
        if (accountGroupInfoDTO != null) {
            accountGroupInfoList = accountGroupInfoService.lambdaQuery()
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getGroupId()), AccountGroupInfo::getGroupId, accountGroupInfoDTO.getGroupId())
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getGroupName()), AccountGroupInfo::getGroupName, accountGroupInfoDTO.getGroupName())
                    .eq(StringUtils.isNotEmpty(accountGroupInfoDTO.getAccountId()), AccountGroupInfo::getAccountId, accountGroupInfoDTO.getAccountId())
                    .eq(StringUtils.isNotEmpty(accountGroupInfoDTO.getUserId()), AccountGroupInfo::getUserId, accountGroupInfoDTO.getUserId())
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getOwner()), AccountGroupInfo::getOwner, accountGroupInfoDTO.getOwner())
                    .like(accountGroupInfoDTO.getOwnerPhone() != null, AccountGroupInfo::getOwnerPhone, accountGroupInfoDTO.getOwnerPhone())
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getDistrict()), AccountGroupInfo::getDistrict, accountGroupInfoDTO.getDistrict())
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getAddress()), AccountGroupInfo::getAddress, accountGroupInfoDTO.getAddress())
                    .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getDescr()), AccountGroupInfo::getDescr, accountGroupInfoDTO.getDescr())
                    .eq(accountGroupInfoDTO.getDt() != null, AccountGroupInfo::getDt, accountGroupInfoDTO.getDt())
                    .gt(accountGroupInfoDTO.getStartTime() != null, AccountGroupInfo::getDt, accountGroupInfoDTO.getStartTime())
                    .lt(accountGroupInfoDTO.getEndTime() != null, AccountGroupInfo::getDt, accountGroupInfoDTO.getEndTime())
                    .eq(AccountGroupInfo::getDeleted, false)
                    .orderByDesc(AccountGroupInfo::getDt)
                    .list();
        }
        //groupId合并
        Set<String> groupIds = new HashSet<>();
        if(accountGroupInfoList.size() > 0 ){
            accountGroupInfoList.forEach(item -> groupIds.add(item.getGroupId()));
        }
        if(accountGroupDTO.getId() != null){
            groupIds.add(String.valueOf(accountGroupDTO.getId()));
        }
        //获取组列表
        LambdaQueryWrapper<AccountGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountGroup::getAppId, accountGroupDTO.getAppId())
                .eq(AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .eq(AccountGroup::getDeleted, false)
                .in(groupIds != null && groupIds.size() > 0,AccountGroup::getId,groupIds)
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(pageNo, pageSize);
        IPage<AccountGroup> groupList = accountGroupService.page(page, queryWrapper);
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupList, pageVo);
        return pageVo;

    }

    /**
     * 自定义账号-组 列表
     * @param accountGroupDTO
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public IPage<AccountGroupVo> customAccountGroupList(AccountGroupDTO accountGroupDTO, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<AccountGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountGroup::getAppId, accountGroupDTO.getAppId())
                .eq(AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .eq(AccountGroup::getDeleted, false)
                .eq(AccountGroup::getId,accountGroupDTO.getId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(pageNo, pageSize);
        IPage<AccountGroup> groupList = accountGroupService.page(page, queryWrapper);
        //复制属性
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupList, pageVo);
        return pageVo;
    }
}
