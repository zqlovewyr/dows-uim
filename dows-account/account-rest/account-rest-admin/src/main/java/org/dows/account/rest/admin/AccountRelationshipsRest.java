package org.dows.account.rest.admin;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.account.api.AccountRelationshipsApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 账号-关系维度信息(AccountRelationships)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:45
 */
@Api(tags = "账号-关系维度信息")
@RequestMapping("accountRelationships")
public interface AccountRelationshipsRest<E, S extends IService> extends AccountRelationshipsApi {


}
