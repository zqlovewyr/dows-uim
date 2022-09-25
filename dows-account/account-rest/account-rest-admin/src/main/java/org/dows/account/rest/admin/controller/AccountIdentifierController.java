package org.dows.account.rest.admin.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.dto.UserDto;
import org.dows.account.entity.AccountIdentifier;
import org.dows.account.service.AccountIdentifierService;
import org.dows.account.rest.admin.AccountIdentifierRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :yangxh
 * @Title: AccountIdentifierController
 * @ProjectName it-cloud
 * @Description: TODO
 * @date 2022/1/719:25
 */
@Api("账号-标识管理")
@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountIdentifierController implements AccountIdentifierRest<AccountIdentifier, AccountIdentifierService> {
    @Override
    public UserDto findByIdentifier(String accountName) {
        return null;
    }
}
