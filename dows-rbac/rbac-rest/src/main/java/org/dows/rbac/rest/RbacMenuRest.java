package org.dows.rbac.rest;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.rbac.api.RbacMenuApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * RBAC-菜单资源(RbacMenu)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "RBAC-菜单资源")
@RequestMapping("rbacMenu")
public interface RbacMenuRest<E, S extends IService> extends RbacMenuApi {


}
