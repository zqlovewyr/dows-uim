package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountRoleApi;
import org.dows.account.biz.enums.EnumAccountRoleStatusCode;
import org.dows.account.biz.exception.AccountRoleException;
import org.dows.account.dto.AccountRoleDTO;
import org.dows.account.entity.AccountOrg;
import org.dows.account.entity.AccountRole;
import org.dows.account.service.AccountRoleService;
import org.dows.account.vo.AccountRoleVo;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author：Administrator
 * @Date：2023/01/31 09:30
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AccountRoleBiz implements AccountRoleApi {

    private final AccountRoleService accountRoleService;

    /**
     * 自定义查询 账号-角色 列表
     *
     * @param accountRoleDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<IPage<AccountRoleVo>> customAccountRoleList(AccountRoleDTO accountRoleDTO) {
        LambdaQueryWrapper<AccountRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(accountRoleDTO.getRoleId()), AccountRole::getRoleId, accountRoleDTO.getRoleId())
                .like(StringUtils.isNotEmpty(accountRoleDTO.getRoleName()), AccountRole::getRoleName, accountRoleDTO.getRoleName())
                .like(StringUtils.isNotEmpty(accountRoleDTO.getRoleCode()), AccountRole::getRoleCode, accountRoleDTO.getRoleCode())
                .eq(accountRoleDTO.getPrincipalType() != null, AccountRole::getPrincipalType, accountRoleDTO.getPrincipalType())
                .like(StringUtils.isNotEmpty(accountRoleDTO.getPrincipalId()), AccountRole::getPrincipalId, accountRoleDTO.getPrincipalId())
                .like(StringUtils.isNotEmpty(accountRoleDTO.getPrincipalName()), AccountRole::getPrincipalName, accountRoleDTO.getPrincipalName())
                .eq(accountRoleDTO.getDt() != null, AccountRole::getDt, accountRoleDTO.getDt())
                .gt(accountRoleDTO.getStartTime() != null, AccountRole::getDt, accountRoleDTO.getStartTime())
                .lt(accountRoleDTO.getEndTime() != null, AccountRole::getDt, accountRoleDTO.getEndTime())
                .eq(AccountRole::getDeleted, false)
                .orderByDesc(AccountRole::getDt);
        Page<AccountRole> page = new Page<>(accountRoleDTO.getPageNo(), accountRoleDTO.getPageSize());
        IPage<AccountRole> rolePage = accountRoleService.page(page, queryWrapper);
        IPage<AccountRoleVo> pageVo = new Page<>();
        BeanUtils.copyProperties(rolePage, pageVo);
        return Response.ok(pageVo);
    }

    @Override
    public Response<AccountRoleVo> getAccountRoleByPrincipalId(String principalId) {
        AccountRole accountRole = accountRoleService.lambdaQuery()
                .eq(AccountRole::getPrincipalId, principalId)
                .one();
        //复制属性
        AccountRoleVo vo = new AccountRoleVo();
        if (accountRole != null) {
            BeanUtils.copyProperties(accountRole, vo);
        }
        return Response.ok(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<AccountRoleVo> getAccountRoleById(Long id) {
        AccountRole accountRole = accountRoleService.getById(id);
        //复制属性
        AccountRoleVo vo = new AccountRoleVo();
        BeanUtils.copyProperties(accountRole, vo);
        return Response.ok(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> updateAccountRoleById(AccountRoleDTO accountRoleDTO) {
        AccountRole role = new AccountRole();
        BeanUtils.copyProperties(accountRoleDTO, role);
        return Response.ok(accountRoleService.updateById(role));
    }

    @Override
    public Response<Long> authAccountRole(AccountRoleDTO accountRoleDTO) {
        AccountRole role = new AccountRole();
        BeanUtils.copyProperties(accountRoleDTO, role);
        boolean flag = accountRoleService.save(role);
        if (flag == false) {
            throw new AccountRoleException(EnumAccountRoleStatusCode.ACCOUNT_ROLE_AUTH_FAIl_EXCEPTION);
        }
        return Response.ok(role.getId());
    }
}
