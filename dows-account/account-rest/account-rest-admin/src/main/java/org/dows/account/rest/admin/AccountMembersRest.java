package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.account.api.AccountMembersApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 账号-会员维度信息(AccountMembers)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
@Api(tags = "账号-会员维度信息")
@RequestMapping("accountMembers")
public interface AccountMembersRest<E, S extends IService> extends AccountMembersApi {


}
