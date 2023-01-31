package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.enums.EnumAccountRolePrincipalType;
import org.dows.account.biz.enums.EnumAccountRoleStatusCode;
import org.dows.account.biz.exception.AccountRoleException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountOrg;
import org.dows.account.entity.AccountRole;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
/*import org.dows.rbac.api.RbacRoleApi;*/
import org.dows.rbac.api.enums.EnumRbacRoleCode;
import org.dows.rbac.api.enums.EnumRbacStatusCode;
import org.dows.rbac.api.exception.RbacException;
import org.dows.rbac.api.vo.RbacRoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
public class AccountOrgBiz {

    private final AccountOrgService accountOrgService;

/*    private final RbacRoleApi rbacRoleApi;*/

    // todo 导入文件
    public void importOrg(String file) {

    }

    // todo 导入组织对象
    public void importOrg(TreeAccountOrgDTO file) {


    }

    // todo 列出所有组织记录
    public void listOrg() {

    }

    // todo 根据条件列出所有层级组织(智能层级，所有组织)
    public void listOrgTree(TreeAccountOrgDTO treeAccountOrgDTO) {


    }

    private final AccountGroupBiz accountGroupBiz;
    private final AccountRoleService accountRoleService;
    private final AccountGroupService accountGroupService;

    /**
     * 创建树形结构 accountOrg 账号-组织
     *
     * @param treeAccountOrgDto recursion TreeDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void createTreeAccountOrg(TreeAccountOrgDTO treeAccountOrgDto) {
        // step1:check static rule
        AccountUtil.validateAccountOrgDTO(treeAccountOrgDto);
        // step2:insert parentAccountOrg
        AccountOrg parentAccountOrg = new AccountOrg();
        BeanUtils.copyProperties(treeAccountOrgDto, parentAccountOrg);
        parentAccountOrg.setOrgId(IdWorker.getIdStr());
        parentAccountOrg.setPid(0L);
        accountOrgService.save(parentAccountOrg);
        // step3:recursion insert sonAccountOrg
        List<TreeAccountOrgDTO> sonAccountOrgList = treeAccountOrgDto.getNextAccountOrgDto();
        this.toArrayTree(sonAccountOrgList, parentAccountOrg.getId());
    }

    /**
     * @param array tree array
     * @param pId   current parent Id
     */
    private void toArrayTree(List<TreeAccountOrgDTO> array, long pId) {
        if (CollectionUtils.isEmpty(array)) {
            return;
        }
        for (TreeAccountOrgDTO orgDTO : array) {
            //check static rule
            AccountUtil.validateAccountOrgDTO(orgDTO);
            //insert childAccountOrg
            AccountOrg accountOrg = new AccountOrg();
            BeanUtils.copyProperties(orgDTO, accountOrg);
            accountOrg.setOrgId(IdWorker.getIdStr());
            accountOrg.setPid(pId);
            accountOrgService.save(accountOrg);
            //recursion
            List<TreeAccountOrgDTO> nextAccountOrgDto = orgDTO.getNextAccountOrgDto();
            this.toArrayTree(nextAccountOrgDto, accountOrg.getId());
        }
    }

    /**
     * create account-org with account-group
     *
     * @param accountOrgDTO single accountOrgDTO
     * @return AccountOrgVo
     */
    @Transactional(rollbackFor = Exception.class)
    public AccountOrgVo createAccountOrg(@RequestBody AccountOrgDTO accountOrgDTO) {
        //step1: check static rule
        AccountUtil.validateAccountOrgDTO(accountOrgDTO);
        //step2: createOrg
        AccountOrg accountOrg = new AccountOrg();
        BeanUtils.copyProperties(accountOrgDTO, accountOrg);
        accountOrg.setOrgId(IdWorker.getIdStr());
        this.accountOrgService.save(accountOrg);
        //step3: exist orgGroups with batchInsert
        List<AccountOrgGroupDTO> accountOrgGroups = accountOrgDTO.getAccountOrgGroups();
        if (!CollectionUtils.isEmpty(accountOrgGroups)) {
            // transitional ordId,OrgName,appId,tenantId
            accountOrgGroups.forEach(item -> {
                item.setOrgId(accountOrg.getOrgId());
                item.setOrgName(accountOrg.getOrgName());
                item.setAppId(accountOrg.getAppId());
                item.setTenantId(accountOrg.getTenantId());
            });
            // create OrgGroup
/*            this.accountGroupBiz.batchInsertGroup(accountOrgGroups);*/
        }
        //step4: create account-role
        Long rbacRoleId = accountOrgDTO.getRbacRoleId();
        if (!Objects.isNull(rbacRoleId) && rbacRoleId == 0) {

    /*        Response<RbacRoleVO> rbacRoleResponse = rbacRoleApi.getById(String.valueOf(rbacRoleId));*/
            Response<RbacRoleVO> rbacRoleResponse = new Response<>();
            RbacRoleVO rbacRoleVO = rbacRoleResponse.getData();
            AccountRole accountRole = AccountRole.builder()
                    .roleId(rbacRoleVO.getId().toString())
                    .roleName(rbacRoleVO.getRoleName())
                    .roleCode(rbacRoleVO.getRoleCode())
                    .principalType(EnumAccountRolePrincipalType.GROUP.getCode())
                    .principalId(accountOrg.getOrgId())
                    .principalName(accountOrg.getOrgName()).build();
            accountRoleService.save(accountRole);
        }
        //step5: return VO
        AccountOrgVo accountOrgVo = new AccountOrgVo();
        BeanUtils.copyProperties(accountOrgVo, accountOrg);
        return accountOrgVo;
    }

    /**
     * Paging query an organization based on accountId by appId
     * Display the results according to different RbacRoleCodes
     * admin: Displays all root organizations of the appId and their corresponding teachers
     * teacher: Show the root organization managed by appId
     * <p>
     * <p>
     * 根据 accountId,appId分页查询组织
     * 根据不同的rbacRoleCode展示结果
     * admin:展示appId所有root组织以及组织所对应的teacher
     * teacher:展示appId所管理的root组织
     *
     * @param appId       appId
     * @param accountId   accountId
     * @param accountName accountName
     * @param pageNo      pageNo
     * @param pageSize    pageSize
     * @return AccountOrgPage
     */
    public IPage<AccountOrgVo> pageAccountOrg(String appId, String accountId, String accountName
            , Integer pageNo, Integer pageSize) {
        // check accountId with role
        List<AccountRole> accountRoles = accountRoleService.lambdaQuery()
                .select(AccountRole::getRoleCode, AccountRole::getRoleId)
                .eq(AccountRole::getPrincipalId, accountId)
                .eq(AccountRole::getPrincipalType, EnumAccountRolePrincipalType.PERSONAL.getCode())
                .eq(AccountRole::getDeleted, false)
                .list();
        if (CollectionUtils.isEmpty(accountRoles)) {
            throw new AccountRoleException(EnumAccountRoleStatusCode.ACCOUNT_ROLE_NOT_EXIST_EXCEPTION);
        }
        // check Current Application Permissions
        List<String> accountRoleRoleIds = accountRoles.stream().map(AccountRole::getRoleId).collect(Collectors.toList());
/*        Response<List<RbacRoleVO>> rbacRoleVOResponse = rbacRoleApi.getByIdListAndAppId(accountRoleRoleIds, appId);*/
        Response<List<RbacRoleVO>> rbacRoleVOResponse = new Response<>();
        List<RbacRoleVO> rbacRoles = rbacRoleVOResponse.getData();
        if (CollectionUtils.isEmpty(rbacRoles)) {
            throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_EXIST_EXCEPTION);
        }
        // account_role super Permissions
        rbacRoles.sort((m1, m2) -> {
            int m1Ordinal = EnumRbacRoleCode.valueOf(m1.getRoleCode()).ordinal();
            int m2Ordinal = EnumRbacRoleCode.valueOf(m2.getRoleCode()).ordinal();
            if (m1Ordinal > m2Ordinal) {
                return 0;
            }
            return -1;
        });
        String roleCode = rbacRoles.get(0).getRoleCode();
        if (EnumRbacRoleCode.RBAC_ROLE_STUDENT_CODE.getCode().equals(roleCode)) {
            throw new RbacException(EnumRbacStatusCode.RBAC_ROLE_NOT_PERMISSION_EXCEPTION);
        }
        IPage<AccountOrg> accountOrgIPage;
        Map<String, List<AccountGroup>> accountGroupMap;
        // admin look all
        if (EnumRbacRoleCode.RBAC_ROLE_ADMIN_CODE.getCode().equals(roleCode)) {
            accountOrgIPage = this.adminPageAccountOrg(appId, pageNo, pageSize);
            List<String> accountOrgIds = new ArrayList<>();
            accountOrgIPage.getRecords().forEach(item -> accountOrgIds.add(item.getOrgId()));
            // orgGroup teacher role
            accountGroupMap = accountGroupBiz
                    .mapAccountGroupByRole(accountOrgIds, EnumRbacRoleCode.RBAC_ROLE_ADMIN_CODE.getCode());
        } else {
            // teacher look self accountOrg
            accountOrgIPage = this.teacherPageAccountOrg(accountId, appId, pageNo, pageSize);
            List<AccountOrg> records = accountOrgIPage.getRecords();
            List<AccountGroup> accountGroupList = new ArrayList<>();
            records.forEach(item -> {
                AccountGroup accountGroup = new AccountGroup();
                accountGroup.setOrgId(item.getOrgId());
                accountGroup.setAccountName(accountName);
                accountGroup.setAccountId(accountId);
                accountGroupList.add(accountGroup);
            });
            accountGroupMap = accountGroupList.stream().collect(Collectors.groupingBy(AccountGroup::getOrgId, HashMap::new, Collectors.toList()));
        }
        IPage<AccountOrgVo> pageVo = new Page<>();
        BeanUtils.copyProperties(accountOrgIPage, pageVo);
        // transfer AccountOrg to AccountOrgVo
        List<AccountOrg> records = accountOrgIPage.getRecords();
        List<AccountOrgVo> accountOrgVoList = new ArrayList<>();
        records.forEach(item -> {
            AccountOrgVo accountOrgVo = new AccountOrgVo();
            BeanUtils.copyProperties(item, accountOrgVo);
            accountOrgVoList.add(accountOrgVo);
            List<AccountGroup> accountGroups = accountGroupMap.get(item.getOrgId());
            if (CollectionUtils.isEmpty(accountGroups)) {
                accountOrgVo.setAccountGroupList(new ArrayList<>());
            } else {
                List<AccountGroupVo> accountGroupList = new ArrayList<>();
                accountGroups.forEach(accountGroup -> {
                    AccountGroupVo accountGroupVo = new AccountGroupVo();
                    BeanUtils.copyProperties(accountGroup, accountGroupVo);
                    accountGroupList.add(accountGroupVo);
                });
                accountOrgVo.setAccountGroupList(accountGroupList);
            }
        });
        pageVo.setRecords(accountOrgVoList);
        return pageVo;
    }

    public IPage<AccountOrg> teacherPageAccountOrg(String accountId, String appId, Integer pageNo, Integer pageSize) {
        // list account_group
        List<AccountGroup> accountGroupList = accountGroupService.lambdaQuery()
                .select(AccountGroup::getOrgId)
                .eq(AccountGroup::getAccountId, accountId)
                .eq(AccountGroup::getAppId, appId)
                .eq(AccountGroup::getDeleted, false)
                .list();
        Page<AccountOrg> page = new Page<>(pageNo, pageSize);
        if (CollectionUtils.isNotEmpty(accountGroupList)) {
            Set<String> orgIds = new HashSet<>();
            accountGroupList.forEach(item -> orgIds.add(item.getOrgId()));
            LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(AccountOrg::getId, AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .in(AccountOrg::getOrgId, orgIds)
                    .eq(AccountOrg::getDeleted, false)
                    .orderByDesc(AccountOrg::getDt);
            return accountOrgService.page(page, queryWrapper);
        }
        return page;
    }

    public IPage<AccountOrg> adminPageAccountOrg(String appId, Integer pageNo, Integer pageSize) {
        // TODO 只获取root节点，以及root节点的教师
        LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<AccountOrg>()
                .eq(AccountOrg::getAppId, appId)
                .eq(AccountOrg::getPid, 0)
                .eq(AccountOrg::getDeleted, false);
        Page<AccountOrg> page = new Page<>(pageNo, pageSize);
        return accountOrgService.page(page, queryWrapper);
    }

}
