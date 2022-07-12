package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.account.api.PrincipalRoleApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 账号-主体角色维度信息(PrincipalRole)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "账号-主体角色维度信息")
@RequestMapping("principalRole")
public interface PrincipalRoleRest<E, S extends IService> extends PrincipalRoleApi {


}
