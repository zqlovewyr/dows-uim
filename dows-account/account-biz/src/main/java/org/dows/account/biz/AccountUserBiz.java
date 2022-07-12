package org.dows.account.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.dto.UserContactDto;
import org.dows.account.crud.entity.AccountUser;
import org.dows.account.crud.service.AccountUserService;
import org.dows.account.crud.service.UserContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :yangxh
 * @Title: AccountUserBiz
 * @ProjectName it-cop
 * @Description: TODO
 * @date 2022/4/718:14
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class AccountUserBiz {
    private final AccountUserService accountUserService;
    private final UserContactService userContactService;


    public List<UserContactDto> getUserContactDto(String pricipalId, Integer pricipalTyp) {
        List<UserContactDto> contactDtos = new ArrayList<>();
        List<String> accountNos = new ArrayList<>();
        if (pricipalTyp == 0) {
            accountNos.add(pricipalId);
        } else {
            List<AccountUser> accountUsers = accountUserService.lambdaQuery().eq(AccountUser::getTenantId, pricipalId).list();
            if (accountUsers != null) {
                accountNos = accountUsers.stream().map(AccountUser::getAccountId).collect(Collectors.toList()).stream().map(a -> a.toString()).collect(Collectors.toList());

            }
        }

        return accountUserService.queryUserContacts(new QueryWrapper<AccountUser>()
                .in("t.account_id", accountNos));


    }

}
