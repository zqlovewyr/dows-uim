package org.dows.account.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountUserApi;
import org.dows.account.biz.constant.BaseConstant;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;
import org.dows.account.biz.util.IDUtil;
import org.dows.account.dto.AccountUserDTO;
import org.dows.account.entity.AccountOrg;
import org.dows.account.entity.AccountUser;
import org.dows.account.service.*;
import org.dows.framework.api.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


/**
 * @author runsix
 */
@Service
@RequiredArgsConstructor
@Slf4j
@DS("uim")
public class AccountUserBiz implements AccountUserApi {
    private final AccountUserService accountUserService;

    @Override
    public Response<Long> createAccountUser(AccountUserDTO accountUserDTO) {
        //1、 校验该账户对应的用户是否已存在
        AccountUser accountUser = accountUserService.lambdaQuery()
                .eq(AccountUser::getUserId, accountUserDTO.getUserId())
                .eq(AccountUser::getAccountId, accountUserDTO.getAccountId())
                .one();
        if(accountUser != null){
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_USER_NOT_EXIST_EXCEPTION);
        }
        //2、创建账户-用户对应关系
        AccountUser model = new AccountUser();
        BeanUtils.copyProperties(accountUserDTO,model);
        boolean flag = accountUserService.save(model);
        if(flag == false){
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_USER_UNION_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId());
    }

    @Override
    public Response getUserByAccountId(String accountId) {
        AccountUser accountUser = accountUserService.lambdaQuery()
                .eq(AccountUser::getAccountId, accountId)
                .one();
        if(Objects.isNull(accountUser)){
            return Response.fail(EnumAccountStatusCode.ACCOUNT_USER_NOT_EXIST_EXCEPTION);
        }
        return Response.ok(accountUser);
    }
}
