package org.dows.account.api;

import org.dows.account.api.dto.AccountAuthClientDto;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账号-授权客户端(AccountAuthClient)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:20
 */
public interface AccountAuthClientApi {
    @PostMapping("thirdLogin")
    Response thirdLogin(@RequestBody AccountAuthClientDto accountAuthClientDto);


}
