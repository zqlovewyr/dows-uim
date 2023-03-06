package org.dows.account.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupInfoApi;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.util.AccountGroupInfoUtil;
import org.dows.account.biz.util.IDUtil;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.entity.AccountGroup;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.service.AccountGroupService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@RequiredArgsConstructor
@Slf4j
@Service
@DS("uim")
public class AccountGroupInfoBiz implements AccountGroupInfoApi {

    private final AccountGroupInfoService accountGroupInfoService;

    private final AccountOrgService accountOrgService;

    private final AccountGroupService accountGroupService;

    private final AccountInstanceBiz accountInstanceBiz;

    /**
     * 自定义账号-机构-组 列表
     *
     * @param accountGroupInfoDTO
     * @return Response<IPage < AccountGroupInfoVo>>
     */
    @Override
    public Response<IPage<AccountGroupInfoVo>> customAccountGroupInfoList(AccountGroupInfoDTO accountGroupInfoDTO) {
        LambdaQueryWrapper<AccountGroupInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountGroupInfoDTO.getOrgId()), AccountGroupInfo::getOrgId, accountGroupInfoDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getOrgName()), AccountGroupInfo::getOrgName, accountGroupInfoDTO.getOrgName())
                .eq(StringUtils.isNotEmpty(accountGroupInfoDTO.getGroupInfoId()), AccountGroupInfo::getGroupInfoId, accountGroupInfoDTO.getGroupInfoId())
                .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getGroupInfoName()), AccountGroupInfo::getGroupInfoName, accountGroupInfoDTO.getGroupInfoName())
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
                .orderByDesc(AccountGroupInfo::getDt);
        Page<AccountGroupInfo> page = new Page<>(accountGroupInfoDTO.getPageNo(), accountGroupInfoDTO.getPageSize());
        IPage<AccountGroupInfo> groupInfoList = accountGroupInfoService.page(page, queryWrapper);
        //复制属性
        IPage<AccountGroupInfoVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupInfoList, pageVo, new String[]{"records"});
        List<AccountGroupInfoVo> voList = new ArrayList<>();
        if (groupInfoList.getRecords() != null && groupInfoList.getRecords().size() > 0) {
            groupInfoList.getRecords().forEach(model -> {
                AccountGroupInfoVo vo = new AccountGroupInfoVo();
                BeanUtils.copyProperties(model, vo);
                String orgId = model.getOrgId();
                //获取组别人数
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

    /**
     * 插入 账号-组-实例
     *
     * @param accountGroupInfoDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Long> insertAccountGroupInfo(AccountGroupInfoDTO accountGroupInfoDTO) {
        //1、判断该组是否存在
        AccountGroupInfo groupInfo = accountGroupInfoService.lambdaQuery()
                .eq(AccountGroupInfo::getGroupInfoName, accountGroupInfoDTO.getGroupInfoName())
                .eq(AccountGroupInfo::getOrgId, accountGroupInfoDTO.getOrgId())
                .one();
        if (groupInfo != null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_IS_EXIST_EXCEPTION);
        }
        //2、插入组-实例表
        AccountGroupInfo accountGroupInfo = new AccountGroupInfo();
        //2.1、设置组实例属性
        BeanUtils.copyProperties(accountGroupInfoDTO, accountGroupInfo);
        accountGroupInfo.setGroupInfoId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
        boolean flag = accountGroupInfoService.save(accountGroupInfo);
        if (flag == false) {
            throw new AccountException(EnumAccountStatusCode.GROUP_INFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(accountGroupInfo.getId());
    }

    /**
     * 批量插入 账号-组-实例
     *
     * @param accountOrgGroupDTOs
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Boolean> batchInsertAccountGroupInfo(List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        AtomicBoolean flag = new AtomicBoolean(true);
        //1、插入组织架构表
        AccountOrg accountOrg = new AccountOrg();
        if (accountOrgGroupDTOs != null && accountOrgGroupDTOs.size() > 0) {
            accountOrgGroupDTOs.forEach(accountOrgGroupDTO -> {
                //1.1、设置组织架构属性
                BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
                accountOrg.setId(null);
                accountOrg.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
                accountOrg.setDescr(accountOrgGroupDTO.getOrgDescr());
                accountOrg.setSorted(accountOrgGroupDTO.getSorted());
                accountOrg.setStatus(accountOrgGroupDTO.getStatus());
                accountOrg.setDt(accountOrgGroupDTO.getDt());
                boolean flagOrg = accountOrgService.save(accountOrg);
                if (flagOrg == false) {
                    flag.set(false);
                }
                //2、插入组-实例表
                AccountGroupInfo accountGroupInfo = new AccountGroupInfo();
                //2.1、设置组实例属性
                BeanUtils.copyProperties(accountOrgGroupDTO, accountGroupInfo);
                accountGroupInfo.setId(null);
                accountGroupInfo.setOrgId(accountOrg.getId().toString());
                boolean flagInfo = accountGroupInfoService.save(accountGroupInfo);
                if (flagInfo == false) {
                    flag.set(false);
                }
            });
        }
        return Response.ok(flag.get());
    }

    /**
     * 编辑 账号-组-实例
     *
     * @param accountGroupInfoDTO
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response updateAccountGroupInfo(AccountGroupInfoDTO accountGroupInfoDTO) {
        //1、更新组-实例表
        LambdaQueryWrapper<AccountGroupInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountGroupInfo::getOrgId, accountGroupInfoDTO.getOrgId().toString());
        AccountGroupInfo groupInfo = accountGroupInfoService.getOne(queryWrapper);
        if (groupInfo == null) {
            return Response.fail(EnumAccountStatusCode.ACCOUNT_ORG_IS_NOT_EXIST);
        }
        //1.1、设置组实例属性
        BeanUtils.copyProperties(accountGroupInfoDTO, groupInfo, new String[]{"id"});
        boolean flagInfo = accountGroupInfoService.updateById(groupInfo);
        if (flagInfo == false) {
            return Response.fail(EnumAccountStatusCode.GROUP_INFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(true);
    }

    /**
     * 查看 账号-组-实例
     *
     * @param id
     */
    @Override
    public Response<AccountGroupInfoVo> getAccountGroupInfoById(Long id) {
        AccountGroupInfo groupInfo = accountGroupInfoService.getById(id);
        //复制属性
        AccountGroupInfoVo vo = new AccountGroupInfoVo();
        BeanUtils.copyProperties(groupInfo, vo);
        //获取允许最大成员数、头像、描述、有效时间、组织类型、状态等
        AccountOrg accountOrg = accountOrgService.lambdaQuery()
                .eq(AccountOrg::getId, vo.getOrgId())
                .one();
        BeanUtils.copyProperties(accountOrg, vo);
        return Response.ok(vo);
    }

    /**
     * 查看 账号-组-实例
     *
     * @param orgId
     * @return
     */
    @Override
    public Response<AccountGroupInfoVo> getAccountGroupInfoByOrgId(String orgId) {
        LambdaQueryWrapper<AccountGroupInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountGroupInfo::getOrgId, orgId);
        AccountGroupInfo groupInfo = accountGroupInfoService.getOne(queryWrapper);
        //复制属性
        AccountGroupInfoVo vo = new AccountGroupInfoVo();
        if (groupInfo != null) {
            BeanUtils.copyProperties(groupInfo, vo);
            //获取允许最大成员数、头像、描述、有效时间、组织类型、状态等
            AccountOrg accountOrg = accountOrgService.lambdaQuery()
                    .eq(AccountOrg::getId, vo.getOrgId())
                    .one();
            BeanUtils.copyProperties(accountOrg, vo);
            vo.setId(accountOrg.getId().toString());
            vo.setGroupDescr(groupInfo.getDescr());
        }
        return Response.ok(vo);
    }

    /**
     * 删除 账号-组-实例
     *
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Boolean> deleteAccountGroupInfoById(String id) {
        //1、获取对应数据
        AccountGroupInfo groupInfo = accountGroupInfoService.getById(id);
        if (groupInfo == null) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_ORG_IS_NOT_EXIST);
        }
        boolean flag = true;
        //1、删除组织架构
        LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
        orgWrapper.set(AccountOrg::getDeleted, true)
                .eq(AccountOrg::getId, groupInfo.getOrgId());
        boolean orgFlag = accountOrgService.update(orgWrapper);
        if (orgFlag == false) {
            flag = false;
        }
        //2、删除组-实例
        LambdaUpdateWrapper<AccountGroupInfo> groupWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
        groupWrapper.set(AccountGroupInfo::getDeleted, true)
                .eq(AccountGroupInfo::getId, id);
        boolean groupFlag = accountGroupInfoService.update(groupWrapper);
        if (groupFlag == false) {
            flag = false;
        }
        return Response.ok(flag);
    }


    /**
     * 批量删除 账号-组-实例
     *
     * @param ids
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Boolean> batchDeleteGroupInfos(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Response.ok(false);
        }
        ids.forEach(item -> {
            //1、删除组织架构
            LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
            orgWrapper.set(AccountOrg::getDeleted, true)
                .eq(AccountOrg::getId, item);
            accountOrgService.update(orgWrapper);
            //2、删除组-实例
            LambdaUpdateWrapper<AccountGroupInfo> groupWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
            groupWrapper.set(AccountGroupInfo::getDeleted, true)
                .eq(AccountGroupInfo::getOrgId, item);
            accountGroupInfoService.update(groupWrapper);
        });
        return Response.ok(true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<AccountGroupInfoVo> createGroupInfo(AccountGroupInfoDTO dto) throws BizException {
        /**静态验证*/
        AccountGroupInfoUtil.validateCreateDTO(dto);
        String accountId = dto.getAccountId();
        AccountInstanceVo accountInstanceVo = accountInstanceBiz.getByAccountId(accountId).getData();
        // 账户ID填写值了就进行验证
        if (null == accountInstanceVo) {
            throw new BizException("未查询到用户组负责人账户");
        }
        // 创建时需要做的操作:生成新的分布式ID
        dto.setGroupInfoId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
        dto.setOwner(accountInstanceVo.getAccountName());
        AccountGroupInfo accountGroupInfo = AccountGroupInfoUtil.buildEntity(dto);
        boolean flag = accountGroupInfoService.save(accountGroupInfo);
        if (!flag) {
            throw new AccountException(EnumAccountStatusCode.GROUP_INFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(AccountGroupInfoUtil.buildVo(accountGroupInfo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<AccountGroupInfoVo> updateGroupInfo(AccountGroupInfoDTO dto) {
        /**用户组可以任意创建*/
        /**静态验证*/
        AccountGroupInfoUtil.validateUpdateDTO(dto);

        /**动态验证*/
        String accountId = dto.getAccountId();
        if (org.springframework.util.StringUtils.hasText(accountId)) {
            // 账户ID填写值了就进行验证
            AccountInstanceVo data = accountInstanceBiz.getByAccountId(accountId).getData();
            if (null == data) {
                throw new BizException("未查询到用户组负责人账户");
            }
        }
        AccountGroupInfo accountGroupInfo = AccountGroupInfoUtil.buildEntity(dto);
        // 根据指定分布式ID进行更新
        boolean flag = accountGroupInfoService.lambdaUpdate()
            .eq(AccountGroupInfo::getGroupInfoId, dto.getGroupInfoId())
            .update(accountGroupInfo);
        if (!flag) {
            throw new AccountException(EnumAccountStatusCode.GROUP_INFO_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(AccountGroupInfoUtil.buildVo(accountGroupInfo));
    }
}
