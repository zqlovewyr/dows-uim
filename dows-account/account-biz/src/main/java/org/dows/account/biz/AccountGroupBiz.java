package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupApi;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.*;
import org.dows.account.service.*;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;
import org.dows.framework.api.StatusCode;
import org.dows.user.entity.UserInstance;
import org.dows.user.service.UserInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
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

    private final AccountRoleService accountRoleService;

    private final AccountUserService accountUserService;

    private final AccountGroupInfoService accountGroupInfoService;

    private final UserInstanceService userInstanceService;

    private final AccountInstanceService accountInstanceService;

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
     * @param accountGroupDTOs account-groups
     */
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> batchInsertGroup(List<AccountGroupDTO> accountGroupDTOs) {
        AtomicBoolean flag = new AtomicBoolean(true);
        if (CollectionUtils.isEmpty(accountGroupDTOs)) {
            return Response.ok(false);
        }
        accountGroupDTOs.forEach(account -> {
            //1、创建账号实例
            AccountInstance accountInstance = new AccountInstance();
            BeanUtils.copyProperties(account, accountInstance);
            boolean accountFlag = accountInstanceService.save(accountInstance);
            if (accountFlag == false) {
                flag.set(false);
            }
            //2、创建用户实例
            UserInstance userInstance = new UserInstance();
            BeanUtils.copyProperties(account, userInstance);
            boolean userFlag = userInstanceService.save(userInstance);
            if (userFlag == false) {
                flag.set(false);
            }
            //3、设置关联关系
            AccountUser accountUser = new AccountUser();
            BeanUtils.copyProperties(account, accountUser);
            accountUser.setUserId(userInstance.getId().toString());
            accountUser.setAccountId(accountInstance.getId().toString());
            boolean unionFlag = accountUserService.save(accountUser);
            if (unionFlag == false) {
                flag.set(false);
            }
            //4、创建组员实例
            AccountGroup accountGroup = new AccountGroup();
            BeanUtils.copyProperties(account, accountGroup);
            accountGroup.setUserId(userInstance.getId().toString());
            boolean groupFlag = accountGroupService.save(accountGroup);
            if (groupFlag == false) {
                flag.set(false);
            }
        });
        return Response.ok(flag.get());
    }

    /**
     * 自定义账号-组-成员 列表
     *
     * @param accountGroupDTO
     * @return Response<IPage < AccountGroupVo>>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountGroupVo>> customAccountGroupList(AccountGroupDTO accountGroupDTO) {
        //1、获取角色对应账号Id
        Set<String> accountIds = new HashSet<>();
        if (accountGroupDTO.getRoleId() != null) {
            List<AccountRole> accountRoleList = accountRoleService.lambdaQuery()
                    .select(AccountRole::getPrincipalId)
                    .eq(AccountRole::getRoleId, accountGroupDTO.getRoleId())
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                    .eq(AccountRole::getDeleted, false)
                    .list();
            if (accountRoleList != null && accountRoleList.size() > 0) {
                accountRoleList.forEach(accountRole -> {
                    accountIds.add(accountRole.getPrincipalId());
                });
            }
        }
        //2、根据团队负责人获取对应组织信息
        Set<String> orgIds = new HashSet<>();
        if (StringUtils.isNotEmpty(accountGroupDTO.getOwnerId())) {
            List<AccountGroupInfo> groupInfoList = accountGroupInfoService.lambdaQuery()
                    .select(AccountGroupInfo::getOrgId)
                    .eq(AccountGroupInfo::getAccountId, accountGroupDTO.getOwnerId())
                    .eq(AccountGroupInfo::getDeleted, false)
                    .list();
            if (groupInfoList != null && groupInfoList.size() > 0) {
                groupInfoList.forEach(accountGroupInfo -> {
                    orgIds.add(accountGroupInfo.getOrgId());
                });
            }
        }
        //3、查询
        LambdaQueryWrapper<AccountGroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountGroupDTO.getAppId()), AccountGroup::getAppId, accountGroupDTO.getAppId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getAccountId()), AccountGroup::getAccountId, accountGroupDTO.getAccountId())
                .in(accountIds != null && accountIds.size() > 0, AccountGroup::getAccountId, accountIds)
                .eq(accountGroupDTO.getId() != null, AccountGroup::getId, accountGroupDTO.getId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .in(orgIds != null && orgIds.size() > 0, AccountGroup::getOrgId, orgIds)
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .eq(AccountGroup::getDeleted, false)
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(accountGroupDTO.getPageNo(), accountGroupDTO.getPageSize());
        IPage<AccountGroup> groupPage = accountGroupService.page(page, queryWrapper);
        //4、获取成员名称
        List<AccountGroup> recordList = groupPage.getRecords();
        List<AccountGroupVo> voList = new ArrayList<>();
        recordList.forEach(record -> {
            AccountGroupVo vo = new AccountGroupVo();
            BeanUtils.copyProperties(record, vo);
            //4.1、设置成员名称和角色
            LambdaQueryWrapper<AccountRole> roleWrapper = new LambdaQueryWrapper<>();
            AccountRole role = accountRoleService.getOne(roleWrapper.eq(AccountRole::getPrincipalId, vo.getAccountId())
                    .eq(AccountRole::getDeleted, false)
                    .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode()));
            vo.setRoleName(role.getRoleName());
            //4.2、设置成员性别
            LambdaQueryWrapper<AccountUser> userWrapper = new LambdaQueryWrapper<>();
            AccountUser user = accountUserService.getOne(userWrapper.eq(AccountUser::getAccountId, vo.getAccountId())
                    .eq(AccountUser::getDeleted, false));
            LambdaQueryWrapper<UserInstance> instanceWrapper = new LambdaQueryWrapper<>();
            UserInstance instance = userInstanceService.getOne(instanceWrapper.eq(UserInstance::getUserId, user.getUserId())
                    .eq(UserInstance::getDeleted, false));
            vo.setGender(instance.getGender());
            //4.2、设置组织架构负责人
            LambdaQueryWrapper<AccountGroupInfo> ownerWrapper = new LambdaQueryWrapper<>();
            AccountGroupInfo groupInfo = accountGroupInfoService.getOne(ownerWrapper.eq(AccountGroupInfo::getOrgId, vo.getOrgId())
                    .eq(AccountGroupInfo::getDeleted, false));
            vo.setOwnerId(groupInfo.getAccountId());
            vo.setOwnerName(groupInfo.getOwner());
        });
        //复制属性
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupPage, pageVo);
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> insertOrUpdateAccountGroup(AccountGroupDTO accountGroupDTO) {
        boolean flag = true;
        //1、创建账号实例
        AccountInstance accountInstance = new AccountInstance();
        BeanUtils.copyProperties(accountGroupDTO, accountInstance);
        boolean accountFlag = accountInstanceService.save(accountInstance);
        if (accountFlag == false) {
            flag = false;
        }
        //2、创建用户实例
        UserInstance userInstance = new UserInstance();
        BeanUtils.copyProperties(accountGroupDTO, userInstance);
        boolean userFlag = userInstanceService.save(userInstance);
        if (userFlag == false) {
            flag = false;
        }
        //3、设置关联关系
        AccountUser accountUser = new AccountUser();
        BeanUtils.copyProperties(accountGroupDTO, accountUser);
        accountUser.setUserId(userInstance.getId().toString());
        accountUser.setAccountId(accountInstance.getId().toString());
        boolean unionFlag = accountUserService.save(accountUser);
        if (unionFlag == false) {
            flag = false;
        }
        //4、创建组员实例
        AccountGroup accountGroup = new AccountGroup();
        BeanUtils.copyProperties(accountGroupDTO, accountGroup);
        accountGroup.setUserId(userInstance.getId().toString());
        boolean groupFlag = accountGroupService.save(accountGroup);
        if (groupFlag == false) {
            flag = false;
        }
        return Response.ok(flag);
    }

    @Override
    // TODO 2023/01/12
    public Response<Object> downloadExcelTemplate(HttpServletResponse response) {
        return null;
    }

    @Override
    // TODO 2023/01/12
    public Response<Object> uploadAccountGroup(MultipartFile file) {
        return null;
    }

    @Override
    public Response<Object> batchDeleteGroupMembers(List<AccountGroupDTO> accountGroupDTOs) {
        if (CollectionUtils.isEmpty(accountGroupDTOs)) {
            return Response.ok(false);
        }
        accountGroupDTOs.forEach(item -> {
            LambdaUpdateWrapper<AccountGroup> groupWrapper = Wrappers.lambdaUpdate(AccountGroup.class);
            groupWrapper.set(AccountGroup::getDeleted, true)
                    .eq(AccountGroup::getOrgId, item.getOrgId());
            accountGroupService.update(groupWrapper);
        });
        return Response.ok(true);
    }
}
