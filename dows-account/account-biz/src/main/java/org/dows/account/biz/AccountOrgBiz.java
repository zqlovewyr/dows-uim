package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountOrgApi;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.biz.util.IDUtil;
import org.dows.account.dto.AccountOrgDTO;
import org.dows.account.dto.TreeAccountOrgDTO;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.dows.user.entity.UserInstance;
import org.springframework.beans.BeanUtils;
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
    private final AccountGroupInfoService groupInfoService;
    private final AccountGroupInfoService accountGroupInfoService;

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
        if (accountOrg != null) {
            throw new AccountException(EnumAccountStatusCode.ORG_EXIST_EXCEPTION);
        }
        //2、创建组织
        AccountOrg model = new AccountOrg();
        BeanUtils.copyProperties(accountOrgDTO, model);
        model.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
        boolean flag = accountOrgService.save(model);
        if (flag == false) {
            throw new AccountException(EnumAccountStatusCode.ORG_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId());
    }

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
        //1、如果存在团队负责人,筛选出团队负责人对应的机构
        Set<String> ids = new HashSet<>();
        if (StringUtils.isNotEmpty(accountOrgDTO.getOwner())) {
            List<AccountGroupInfo> groupInfoList = accountGroupInfoService.lambdaQuery()
                    .select(AccountGroupInfo::getOrgId)
                    .eq(AccountGroupInfo::getOwner, accountOrgDTO.getOwner())
                    .list();
            if (groupInfoList != null && groupInfoList.size() > 0) {
                groupInfoList.forEach(accountGroupInfo -> {
                    ids.add(accountGroupInfo.getOrgId());
                });
            }
        }
        if (accountOrgDTO.getIds() != null && accountOrgDTO.getIds().size() > 0) {
            ids.addAll(accountOrgDTO.getIds());
        }

        LambdaQueryWrapper<AccountOrg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ids != null && ids.size() > 0, AccountOrg::getId, ids)
                .eq(accountOrgDTO.getPid() != null, AccountOrg::getPid, accountOrgDTO.getPid())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgId()), AccountOrg::getOrgId, accountOrgDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgName()), AccountOrg::getOrgName, accountOrgDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getOrgCode()), AccountOrg::getOrgCode, accountOrgDTO.getOrgCode())
                .eq(StringUtils.isNotEmpty(accountOrgDTO.getNameLetters()), AccountOrg::getNameLetters, accountOrgDTO.getNameLetters())
                .eq(StringUtils.isNotEmpty(accountOrgDTO.getProfile()), AccountOrg::getProfile, accountOrgDTO.getProfile())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getAppId()), AccountOrg::getAppId, accountOrgDTO.getAppId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getTenantId()), AccountOrg::getTenantId, accountOrgDTO.getTenantId())
                .like(StringUtils.isNotEmpty(accountOrgDTO.getDescr()), AccountOrg::getDescr, accountOrgDTO.getDescr())
                .eq(accountOrgDTO.getOrgType() != null, AccountOrg::getOrgType, accountOrgDTO.getOrgType())
                .eq(accountOrgDTO.getSorted() != null, AccountOrg::getSorted, accountOrgDTO.getSorted())
                .eq(accountOrgDTO.getStatus() != null, AccountOrg::getStatus, accountOrgDTO.getStatus())
                .eq(accountOrgDTO.getDt() != null, AccountOrg::getDt, accountOrgDTO.getDt())
                .gt(accountOrgDTO.getStartTime() != null, AccountOrg::getDt, accountOrgDTO.getStartTime())
                .lt(accountOrgDTO.getEndTime() != null, AccountOrg::getDt, accountOrgDTO.getEndTime());
        //判断按什么排序,如果不按名称和编码排序，则按默认的创建时间排序
        if (StringUtils.isNotEmpty(accountOrgDTO.getOrgCodeType())) {
            if (accountOrgDTO.getOrgCodeType().equals("down")) {
                queryWrapper.orderByDesc(AccountOrg::getOrgCode);
            }
            if (accountOrgDTO.getOrgCodeType().equals("up")) {
                queryWrapper.orderByAsc(AccountOrg::getOrgCode);
            }
        } else if (StringUtils.isNotEmpty(accountOrgDTO.getOrgNameType())) {
            if (accountOrgDTO.getOrgNameType().equals("down")) {
                queryWrapper.orderByDesc(AccountOrg::getOrgName);
            }
            if (accountOrgDTO.getOrgNameType().equals("up")) {
                queryWrapper.orderByAsc(AccountOrg::getOrgName);
            }
        } else if (StringUtils.isNotEmpty(accountOrgDTO.getDtType())) {
            if (accountOrgDTO.getDtType().equals("down")) {
                queryWrapper.orderByDesc(AccountOrg::getDt);
            }
            if (accountOrgDTO.getDtType().equals("up")) {
                queryWrapper.orderByAsc(AccountOrg::getDt);
            }
        } else{
            queryWrapper.orderByDesc(AccountOrg::getDt);
        }
        Page<AccountOrg> page = new Page<>(accountOrgDTO.getPageNo(), accountOrgDTO.getPageSize());
        IPage<AccountOrg> orgList = accountOrgService.page(page, queryWrapper);
        //复制属性
        IPage<AccountOrgVo> pageVo = new Page<>();
        BeanUtils.copyProperties(orgList, pageVo, new String[]{"records"});
        List<AccountOrgVo> voList = new ArrayList<>();
        if (orgList.getRecords() != null && orgList.getRecords().size() > 0) {
            orgList.getRecords().forEach(model -> {
                AccountOrgVo vo = new AccountOrgVo();
                BeanUtils.copyProperties(model, vo);
                String orgId = model.getId().toString();
                //获取机构人数
                Integer num = accountGroupService.lambdaQuery()
                        .eq(AccountGroup::getOrgId, orgId)
                        .list().size();
                vo.setCurrentNum(num);
                //获取当前负责人电话
                AccountGroupInfo info = groupInfoService.lambdaQuery().eq(AccountGroupInfo::getOrgId, orgId).one();
                if (info != null) {
                    vo.setTelePhone(info.getOwnerPhone());
                    vo.setOwnerName(info.getOwner());
                    vo.setGroupDescr(info.getDescr());
                }
                // TODO 否则，从组织架构基础表找(account_org_info)
                //主键id转换为String
                vo.setId(model.getId().toString());
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
        accountOrg.setId(Long.valueOf(accountOrgDTO.getId()));
        boolean flag = accountOrgService.updateById(accountOrg);
        if (flag == false) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_UPDATE_FAIL_EXCEPTION);
        }
    }

    @Override
    public Response<List<AccountOrgVo>> getAccountOrgByPId(String id) {
        List<AccountOrgVo> voList = new ArrayList<>();
        List<AccountOrg> accountOrgList = accountOrgService.lambdaQuery()
                .eq(AccountOrg::getPid, id)
                .list();
        if (accountOrgList != null && accountOrgList.size() > 0) {
            accountOrgList.forEach(accountOrg -> {
                AccountOrgVo vo = new AccountOrgVo();
                BeanUtils.copyProperties(accountOrg, vo);
                vo.setId(accountOrg.getId().toString());
                voList.add(vo);
            });
        }
        return Response.ok(voList);
    }

    @Override
    public Response<AccountOrgVo> getAccountOrgById(String id) {
        AccountOrg model = accountOrgService.getById(Long.valueOf(id));
        AccountOrgVo vo = new AccountOrgVo();
        if (model != null) {
            BeanUtils.copyProperties(model, vo);
            vo.setId(model.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAccountOrgById(String id) {
        //1、删除组织架构
        AccountOrg accountOrg = accountOrgService.lambdaQuery()
                .eq(AccountOrg::getId, Long.valueOf(id))
                .one();
        if (accountOrg == null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_NOT_EXIST_EXCEPTION);
        }
        LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
        orgWrapper.set(AccountOrg::getDeleted, true)
                .eq(AccountOrg::getId, Long.valueOf(id));
        boolean flag1 = accountOrgService.update(orgWrapper);
        if (!flag1) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_UPDATE_FAIL_EXCEPTION);
        }
        //2、如果有成员，删除成员信息
        List<AccountGroup> accountGroupList = accountGroupService.lambdaQuery()
                .eq(AccountGroup::getOrgId, id)
                .list();
        if (accountGroupList != null && accountGroupList.size() > 0) {
            LambdaUpdateWrapper<AccountGroup> groupWrapper = Wrappers.lambdaUpdate(AccountGroup.class);
            groupWrapper.set(AccountGroup::getDeleted, true)
                    .eq(AccountGroup::getOrgId, id);
            boolean flag2 = accountGroupService.update(groupWrapper);
            if (!flag2) {
                throw new AccountException(EnumAccountStatusCode.ACCOUNT_GROUP_UPDATE_FAIL_EXCEPTION);
            }
        }
        //3、删除组织信息
        AccountGroupInfo accountGroupInfo = accountGroupInfoService.lambdaQuery()
                .eq(AccountGroupInfo::getOrgId, id)
                .one();
        if (accountGroupInfo != null) {
            LambdaUpdateWrapper<AccountGroupInfo> infoWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
            infoWrapper.set(AccountGroupInfo::getDeleted, true)
                    .eq(AccountGroupInfo::getOrgId, id);
            boolean flag3 = accountGroupInfoService.update(infoWrapper);
            if (!flag3) {
                throw new AccountException(EnumAccountStatusCode.ACCOUNT_GROUP_INFO_NOT_EXIST_EXCEPTION);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteAccountOrgs(List<String> ids) {
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                //1、删除组织架构
                AccountOrg accountOrg = accountOrgService.lambdaQuery()
                        .eq(AccountOrg::getId, id)
                        .one();
                if (accountOrg == null) {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_NOT_EXIST_EXCEPTION);
                }
                LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
                orgWrapper.set(AccountOrg::getDeleted, true)
                        .eq(AccountOrg::getId, id);
                boolean flag1 = accountOrgService.update(orgWrapper);
                if (!flag1) {
                    throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_UPDATE_FAIL_EXCEPTION);
                }
                //2、如果有成员，删除成员信息
                AccountGroup accountGroup = accountGroupService.lambdaQuery()
                        .eq(AccountGroup::getOrgId, id)
                        .one();
                if (accountGroup != null) {
                    LambdaUpdateWrapper<AccountGroup> groupWrapper = Wrappers.lambdaUpdate(AccountGroup.class);
                    groupWrapper.set(AccountGroup::getDeleted, true)
                            .eq(AccountGroup::getOrgId, id);
                    boolean flag2 = accountGroupService.update(groupWrapper);
                    if (!flag2) {
                        throw new AccountException(EnumAccountStatusCode.ACCOUNT_GROUP_UPDATE_FAIL_EXCEPTION);
                    }
                }
                //3、删除组织信息
                AccountGroupInfo accountGroupInfo = accountGroupInfoService.lambdaQuery()
                        .eq(AccountGroupInfo::getOrgId, id)
                        .one();
                if (accountGroupInfo != null) {
                    LambdaUpdateWrapper<AccountGroupInfo> infoWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
                    infoWrapper.set(AccountGroupInfo::getDeleted, true)
                            .eq(AccountGroupInfo::getOrgId, id);
                    boolean flag3 = accountGroupInfoService.update(infoWrapper);
                    if (!flag3) {
                        throw new AccountException(EnumAccountStatusCode.ACCOUNT_GROUP_INFO_NOT_EXIST_EXCEPTION);
                    }
                }
            }
        }
    }
}
