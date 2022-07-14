package org.dows.rbac.rest;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.rbac.api.RbacResourceApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * RBAC-应用资源(RbacResource)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "RBAC-应用资源")
@RequestMapping("rbacResource")
public interface RbacResourceRest<E, S extends IService> extends RbacResourceApi {


}
