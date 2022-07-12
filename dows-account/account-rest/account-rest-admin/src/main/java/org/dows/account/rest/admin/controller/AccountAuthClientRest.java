package org.dows.account.rest.admin.controller;

import lombok.RequiredArgsConstructor;
import org.dows.account.api.AccountAuthClientApi;
import org.dows.account.api.dto.AccountAuthClientDto;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 账号-授权客户端(AccountAuthClient)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
//@Api(tags = "账号-授权客户端")
@RestController
@RequestMapping("accountAuthClient")
@RequiredArgsConstructor
public class AccountAuthClientRest implements AccountAuthClientApi {

    //private final  AccountAuthClientBiz accountAuthClientBiz;

    @Override
    public Response thirdLogin(AccountAuthClientDto accountAuthClientDto) {
        //accountAuthClientBiz.doLogin();
        return null;
    }
}
