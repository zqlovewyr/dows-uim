package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountOrgApi;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.biz.util.IDUtil;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;


@RequiredArgsConstructor
@Slf4j
@Service
public class AccountOrgBiz implements AccountOrgApi {

    private final AccountOrgService accountOrgService;
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
    public Response<Long> createAccountOrg(@RequestBody AccountOrgDTO accountOrgDTO) {
        //1、 校验该组织是否已存在
        AccountOrg accountOrg = accountOrgService.lambdaQuery()
                .eq(AccountOrg::getOrgName, accountOrgDTO.getOrgName())
                .eq(AccountOrg::getOrgCode, accountOrgDTO.getOrgCode())
                .one();
        if(accountOrg != null){
            throw new AccountException(EnumAccountStatusCode.ORG_NOT_EXIST_EXCEPTION);
        }
        //2、创建组织
        AccountOrg model = new AccountOrg();
        BeanUtils.copyProperties(accountOrgDTO,model);
        model.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
        boolean flag = accountOrgService.save(model);
        if(flag == false){
            throw new AccountException(EnumAccountStatusCode.ORG_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId());
    }

        /*
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
         *//*
    public IPage<AccountOrgVO> pageAccountOrg(String appId, String accountId, String accountName
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
*//*        Response<List<RbacRoleVO>> rbacRoleVOResponse = rbacRoleApi.getByIdListAndAppId(accountRoleRoleIds, appId);*//*
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
        IPage<AccountOrgVO> pageVo = new Page<>();
        BeanUtils.copyProperties(accountOrgIPage, pageVo);
        // transfer AccountOrg to AccountOrgVo
        List<AccountOrg> records = accountOrgIPage.getRecords();
        List<AccountOrgVO> accountOrgVoList = new ArrayList<>();
        records.forEach(item -> {
            AccountOrgVO accountOrgVo = new AccountOrgVO();
            BeanUtils.copyProperties(item, accountOrgVo);
            accountOrgVoList.add(accountOrgVo);
            List<AccountGroup> accountGroups = accountGroupMap.get(item.getOrgId());
            if (CollectionUtils.isEmpty(accountGroups)) {
                accountOrgVo.setAccountGroupList(new ArrayList<>());
            } else {
                List<AccountGroupVO> accountGroupList = new ArrayList<>();
                accountGroups.forEach(accountGroup -> {
                    AccountGroupVO accountGroupVo = new AccountGroupVO();
                    BeanUtils.copyProperties(accountGroup, accountGroupVo);
                    accountGroupList.add(accountGroupVo);
                });
                accountOrgVo.setAccountGroupList(accountGroupList);
            }
        });
        pageVo.setRecords(accountOrgVoList);
        return pageVo;*/
/*        return null;
    }*/

    public IPage<AccountOrgVo> teacherPageAccountOrg(String accountId, String appId, Integer pageNo, Integer pageSize) {
        // list account_group
        List<AccountGroup> accountGroupList = accountGroupService.lambdaQuery()
                .select(AccountGroup::getOrgId)
                .eq(AccountGroup::getAccountId, accountId)
                .eq(AccountGroup::getAppId, appId)
                .eq(AccountGroup::getDeleted, false)
                .list();
        Page<AccountOrg> page = new Page<>(pageNo, pageSize);
        Page<AccountOrgVo> voPage = new Page<>();
        if (CollectionUtils.isNotEmpty(accountGroupList)) {
            Set<String> orgIds = new HashSet<>();
            accountGroupList.forEach(item -> orgIds.add(item.getOrgId()));
            LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(AccountOrg::getId, AccountOrg::getOrgId, AccountOrg::getOrgName)
                    .in(AccountOrg::getOrgId, orgIds)
                    .eq(AccountOrg::getDeleted, false)
                    .orderByDesc(AccountOrg::getDt);
            page = accountOrgService.page(page, queryWrapper);
            BeanUtils.copyProperties(page, voPage);
            return voPage;
        }
        BeanUtils.copyProperties(page, voPage);
        return voPage;
    }

    public IPage<AccountOrgVo> adminPageAccountOrg(String appId, Integer pageNo, Integer pageSize) {
        // TODO 只获取root节点，以及root节点的教师
        LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<AccountOrg>()
                .eq(AccountOrg::getAppId, appId)
                .eq(AccountOrg::getPid, 0)
                .eq(AccountOrg::getDeleted, false);
        Page<AccountOrg> page = new Page<>(pageNo, pageSize);
        Page<AccountOrgVo> voPage = new Page<>();
        page = accountOrgService.page(page, queryWrapper);
        BeanUtils.copyProperties(page, voPage);
        return voPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountOrgVo>> customAccountOrgList(AccountOrgDTO accountOrgDTO) {
        LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(accountOrgDTO.getPid() != null, AccountOrg::getPid, accountOrgDTO.getPid())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgId()), AccountOrg::getOrgId, accountOrgDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgName()), AccountOrg::getOrgName, accountOrgDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgCode()), AccountOrg::getOrgCode, accountOrgDTO.getOrgCode())
                .eq(StringUtils.isNotEmpty(accountOrgDTO.getNameLetters()), AccountOrg::getNameLetters, accountOrgDTO.getNameLetters())
                .eq(StringUtils.isNotEmpty(accountOrgDTO.getProfile()), AccountOrg::getProfile, accountOrgDTO.getProfile())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getAppId()), AccountOrg::getAppId, accountOrgDTO.getAppId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getTenantId()), AccountOrg::getTenantId, accountOrgDTO.getTenantId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getDescr()), AccountOrg::getDescr, accountOrgDTO.getDescr())
                .eq(accountOrgDTO.getOrgType() != null, AccountOrg::getOrgType, accountOrgDTO.getOrgType())
                .eq(StringUtils.isNotEmpty(accountOrgDTO.getSorted()), AccountOrg::getSorted, accountOrgDTO.getSorted())
                .eq(accountOrgDTO.getStatus() != null, AccountOrg::getStatus, accountOrgDTO.getStatus())
                .eq(accountOrgDTO.getDt() != null, AccountOrg::getDt, accountOrgDTO.getDt())
                .gt(accountOrgDTO.getStartTime() != null, AccountOrg::getDt, accountOrgDTO.getStartTime())
                .lt(accountOrgDTO.getEndTime() != null, AccountOrg::getDt, accountOrgDTO.getEndTime())
                .gt(accountOrgDTO.getIndate() != null, AccountOrg::getIndate, accountOrgDTO.getIndate())
                .lt(accountOrgDTO.getExpdate() != null, AccountOrg::getIndate, accountOrgDTO.getExpdate())
                .orderByDesc(AccountOrg::getDt);
        Page<AccountOrg> page = new Page<>(accountOrgDTO.getPageNo(), accountOrgDTO.getPageSize());
        IPage<AccountOrg> orgList = accountOrgService.page(page, queryWrapper);
        //复制属性
        IPage<AccountOrgVo> pageVo = new Page<>();
        BeanUtils.copyProperties(orgList, pageVo,new String[]{"records"});
        List<AccountOrgVo> voList = new ArrayList<>();
        if (orgList.getRecords() != null && orgList.getRecords().size() > 0) {
            orgList.getRecords().forEach(model -> {
                AccountOrgVo vo = new AccountOrgVo();
                BeanUtils.copyProperties(model,vo);
                String orgId = model.getOrgId();
                //获取机构人数
                Integer num = accountGroupService.lambdaQuery()
                        .eq(AccountGroup::getOrgId, orgId)
                        .list().size();
                vo.setNum(num);
                voList.add(vo);
            });
        }
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    public void updateAccountOrgById(AccountOrgDTO accountOrgDTO) {
        AccountOrg accountOrg = new AccountOrg();
        BeanUtils.copyProperties(accountOrgDTO, accountOrg);
        boolean flag = accountOrgService.updateById(accountOrg);
        if(flag == false){
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_UPDATE_FAIL_EXCEPTION);
        }
    }
}
