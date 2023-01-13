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
import org.dows.account.api.AccountGroupInfoApi;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.util.IDUtil;
import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.dto.AccountOrgGroupDTO;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountGroupInfoService;
import org.dows.account.service.AccountOrgService;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.framework.api.Response;
import org.dows.framework.api.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountGroupInfoBiz implements AccountGroupInfoApi {

    private final AccountGroupInfoService accountGroupInfoService;

    private final AccountOrgService accountOrgService;

    /**
     * 自定义账号-组负责人 列表
     *
     * @param accountGroupInfoDTO
     * @return Response<IPage < AccountGroupInfoVo>>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountGroupInfoVo>> customAccountGroupInfoList(AccountGroupInfoDTO accountGroupInfoDTO) {
        LambdaQueryWrapper<AccountGroupInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(accountGroupInfoDTO.getOrgId()),AccountGroupInfo::getOrgId,accountGroupInfoDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupInfoDTO.getOrgName()),AccountGroupInfo::getOrgName,accountGroupInfoDTO.getOrgName())
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
        BeanUtils.copyProperties(groupInfoList, pageVo);
        return Response.ok(pageVo);
    }

    /**
     * 插入 账号-组-实例
     *
     * @param accountOrgGroupDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> insertAccountGroup(AccountOrgGroupDTO accountOrgGroupDTO) {
        boolean flag = true;
        //1、插入组织架构表
        AccountOrg accountOrg = new AccountOrg();
        //1.1、设置组织架构属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
        accountOrg.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
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

    /**
     * 批量插入 账号-组-实例
     *
     * @param accountOrgGroupDTOs
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> batchInsertAccountGroup(List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        AtomicBoolean flag = new AtomicBoolean(true);
        //1、插入组织架构表
        AccountOrg accountOrg = new AccountOrg();
        if(accountOrgGroupDTOs != null && accountOrgGroupDTOs.size() > 0) {
            accountOrgGroupDTOs.forEach(accountOrgGroupDTO->{
                //1.1、设置组织架构属性
                BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
                accountOrg.setId(null);
                accountOrg.setOrgId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
                accountOrg.setDescr(accountOrgGroupDTO.getOrgDescr());
                accountOrg.setSorted(accountOrgGroupDTO.getOrgSorted().toString());
                accountOrg.setStatus(accountOrgGroupDTO.getOrgStatus().toString());
                accountOrg.setDt(accountOrgGroupDTO.getOrgDt());
                boolean flagOrg = accountOrgService.save(accountOrg);
                if (flagOrg == false) {
                    flag.set(false);
                }
                //2、插入组-实例表
                AccountGroupInfo accountGroupInfo = new AccountGroupInfo();
                //2.1、设置组实例属性
                BeanUtils.copyProperties(accountOrgGroupDTO, accountGroupInfo);
                accountGroupInfo.setId(null);
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
     * @param accountOrgGroupDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> updateAccountGroup(AccountOrgGroupDTO accountOrgGroupDTO) {
        boolean flag = true;
        //1、插入组织架构表
        AccountOrg accountOrg = new AccountOrg();
        //1.1、设置组织架构属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountOrg);
        accountOrg.setDescr(accountOrgGroupDTO.getOrgDescr());
        accountOrg.setSorted(accountOrgGroupDTO.getOrgSorted().toString());
        accountOrg.setStatus(accountOrgGroupDTO.getOrgStatus().toString());
        accountOrg.setDt(accountOrgGroupDTO.getOrgDt());
        boolean flagOrg = accountOrgService.saveOrUpdate(accountOrg);
        if (flagOrg == false) {
            flag = false;
        }
        //2、插入组-实例表
        AccountGroupInfo accountGroupInfo = new AccountGroupInfo();
        //2.1、设置组实例属性
        BeanUtils.copyProperties(accountOrgGroupDTO, accountGroupInfo);
        boolean flagInfo = accountGroupInfoService.saveOrUpdate(accountGroupInfo);
        if (flagInfo == false) {
            flag = false;
        }
        return Response.ok(flag);
    }

    /**
     * 删除 账号-组-实例
     *
     * @param accountOrgGroupDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> deleteAccountGroup(AccountOrgGroupDTO accountOrgGroupDTO) {
        boolean flag = true;
        //1、删除组织架构
        LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
        orgWrapper.set(AccountOrg::getDeleted, true)
                .eq(AccountOrg::getOrgId, accountOrgGroupDTO.getOrgId());
        boolean orgFlag = accountOrgService.update(orgWrapper);
        if(orgFlag == false){
            flag = false;
        }
        //2、删除组-实例
        LambdaUpdateWrapper<AccountGroupInfo> groupWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
        groupWrapper.set(AccountGroupInfo::getDeleted, true)
                .eq(AccountGroupInfo::getOrgId, accountOrgGroupDTO.getOrgId());
        boolean groupFlag = accountGroupInfoService.update(groupWrapper);
        if(groupFlag == false){
            flag = false;
        }
        return Response.ok(flag);
    }


    /**
     * 批量删除 账号-组-实例
     *
     * @param accountOrgGroupDTOs
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> batchDeleteGroups(List<AccountOrgGroupDTO> accountOrgGroupDTOs) {
        if (CollectionUtils.isEmpty(accountOrgGroupDTOs)) {
            return Response.ok(false);
        }
        accountOrgGroupDTOs.forEach(item->{
            //1、删除组织架构
            LambdaUpdateWrapper<AccountOrg> orgWrapper = Wrappers.lambdaUpdate(AccountOrg.class);
            orgWrapper.set(AccountOrg::getDeleted, true)
                    .eq(AccountOrg::getOrgId, item.getOrgId());
            accountOrgService.update(orgWrapper);
            //2、删除组-实例
            LambdaUpdateWrapper<AccountGroupInfo> groupWrapper = Wrappers.lambdaUpdate(AccountGroupInfo.class);
            groupWrapper.set(AccountGroupInfo::getDeleted, true)
                    .eq(AccountGroupInfo::getOrgId, item.getOrgId());
            accountGroupInfoService.update(groupWrapper);
        });
        return Response.ok(true);
    }
}
