package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountGroupApi;
import org.dows.account.dto.AccountGroupDTO;
import org.dows.account.vo.AccountGroupVo;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.entity.UserInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author：jiangxia
 * @Date：2023/01/10 13:26
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserInstanceBiz implements UserInstanceApi {
    private final AccountGroupApi accountGroupBiz;

    /**
     * 自定义查询 组成员列表
     *
     * @param userInstanceDTO
     * @return Response<IPage < UserInstanceVo>>
/*     *//*
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<UserInstanceVo>> userInstanceUnionList(UserInstanceDTO userInstanceDTO) {

        LambdaQueryWrapper<UserInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInstance::getAppId, accountGroupDTO.getAppId())
                .eq(UserInstance::getAccountId, accountGroupDTO.getAccountId())
                .eq(UserInstance::getDeleted, false)
                .eq(UserInstance::getId, accountGroupDTO.getId())
                .eq(UserInstance::getId, accountGroupDTO.getId())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getOrgId()), AccountGroup::getOrgId, accountGroupDTO.getOrgId())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getOrgName()), AccountGroup::getOrgName, accountGroupDTO.getOrgName())
                .like(StringUtils.isNotEmpty(accountGroupDTO.getAccountName()), AccountGroup::getAccountName, accountGroupDTO.getAccountName())
                .eq(StringUtils.isNotEmpty(accountGroupDTO.getTenantId()), AccountGroup::getTenantId, accountGroupDTO.getTenantId())
                .eq(accountGroupDTO.getDt() != null, AccountGroup::getDt, accountGroupDTO.getDt())
                .gt(accountGroupDTO.getStartTime() != null, AccountGroup::getDt, accountGroupDTO.getStartTime())
                .lt(accountGroupDTO.getEndTime() != null, AccountGroup::getDt, accountGroupDTO.getEndTime())
                .orderByDesc(AccountGroup::getDt);
        Page<AccountGroup> page = new Page<>(accountGroupDTO.getPageNo(), accountGroupDTO.getPageSize());
        IPage<AccountGroup> groupList = accountGroupService.page(page, queryWrapper);
        //复制属性
        IPage<AccountGroupVo> pageVo = new Page<>();
        BeanUtils.copyProperties(groupList, pageVo);
        return Response.ok(pageVo);
    }*/
}
