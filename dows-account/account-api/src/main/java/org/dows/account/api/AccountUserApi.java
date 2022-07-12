package org.dows.account.api;

import org.dows.account.api.dto.UserContactDto;
import org.dows.framework.api.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 账号-用户维度信息(AccountUser)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
public interface AccountUserApi {
    @RequestMapping(value = "/getUserContactDtoByPricipal", method = RequestMethod.GET)
    public Response<List<UserContactDto>> getUserContactDto(@RequestParam("pricipalId") String pricipalId, @RequestParam("pricipalTyp") Integer pricipalTyp);
}
