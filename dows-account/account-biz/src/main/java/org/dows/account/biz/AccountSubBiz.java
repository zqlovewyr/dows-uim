package org.dows.account.biz;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.dto.AccountInstanceResDTO;
import org.dows.account.entity.AccountSub;
import org.dows.account.entity.AccountSubAuth;
import org.dows.account.form.AccountSubForm;
import org.dows.account.service.AccountSubAuthService;
import org.dows.account.service.AccountSubService;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.api.Response;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountSubBiz {
    private final AccountSubService accountSubService;
    private final AccountSubAuthService accountSubAuthService;
    private final AccountInstanceBiz accountInstanceBiz;

    public Response saveorupdate(AccountSubForm form) {
        //新增子账号
        AccountInstanceResDTO accountInstanceResDTO = new AccountInstanceResDTO();
        accountInstanceResDTO.setAccountName(form.getAccountName());
        accountInstanceResDTO.setPassword(form.getPassword());
        accountInstanceResDTO.setAccountType(3);
        accountInstanceResDTO.setGender(ObjectUtils.isNotEmpty(form.getSex()) ? form.getSex() : 0);
        if (ObjectUtils.isNotEmpty(form.getId())) {
            AccountInstanceResVo accountInstanceInfo = accountInstanceBiz.getAccountInstanceInfo(accountSubService.getById(form.getId()).getAccountId());
            accountInstanceResDTO.setAccountId(accountInstanceInfo.getAccountId());
            accountInstanceResDTO.setId(accountInstanceInfo.getId());
        }
        AccountInstanceVo accountInstanceVo = accountInstanceBiz.saveOrUpdateAccountInstance(accountInstanceResDTO);
        form.setAccountId(accountInstanceVo.getAccountId().toString());
        //新增账号层级关系
        AccountSub accountSub = BeanUtil.copyProperties(form, AccountSub.class);
        accountSubService.saveOrUpdate(accountSub);
        //新增账号权限
        AccountSubAuth accountSubAuth = AccountSubAuth.builder().accountId(accountInstanceVo.getAccountId().toString()).build();
        if (ObjectUtils.isNotEmpty(form.getId())) {
            accountSubAuth.setId(accountSubAuthService.lambdaQuery().eq(AccountSubAuth::getAccountId, accountInstanceVo.getAccountId().toString()).list().get(0).getId());
        }
        accountSubAuthService.saveOrUpdate(accountSubAuth);
        return Response.ok();
    }

}
