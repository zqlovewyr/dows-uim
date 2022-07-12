package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.account.api.AccountGroupApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 账号-账号组维度信息(AccountGroup)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
@Api(tags = "账号-账号组维度信息")
@RequestMapping("accountGroup")
public interface AccountGroupRest<E, S extends IService> extends AccountGroupApi {


}
