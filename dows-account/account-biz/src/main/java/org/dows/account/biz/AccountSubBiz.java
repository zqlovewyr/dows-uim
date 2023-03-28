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
import org.dows.store.api.StoreInstanceApi;
import org.dows.store.util.RandomStrUtil;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountSubBiz {
    private final AccountSubService accountSubService;
    private final AccountSubAuthService accountSubAuthService;
    private final AccountInstanceBiz accountInstanceBiz;
    private final StoreInstanceApi storeInstanceApi;

    public Response saveorupdate(AccountSubForm form) {
        //新增子账号
        AccountInstanceResDTO accountInstanceResDTO = new AccountInstanceResDTO();
        String accountName = form.getAccountName();
        //自动生成账号密码
        if (ObjectUtils.isEmpty(accountName)) {
            accountName = RandomStrUtil.Z + RandomStrUtil.getRandomNo4(8);
            form.setAccountName(accountName);
        }
        String password = form.getPassword();
        if (ObjectUtils.isEmpty(password)) {
            password = RandomStrUtil.DEFAULT_PASSWORD;
            form.setPassword(password);
        }
        accountInstanceResDTO.setAccountName(accountName);
        accountInstanceResDTO.setPassword(password);
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
        //判断门店信息
        if (ObjectUtils.isNotEmpty(accountSub.getStoreId())) {
            accountSub.setStoreName(storeInstanceApi.getStoreById(accountSub.getStoreId()).getName());
        }
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
