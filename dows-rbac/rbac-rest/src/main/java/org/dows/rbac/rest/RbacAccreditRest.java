package org.dows.rbac.rest;

import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.Api;
import org.dows.rbac.api.RbacAccreditApi;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * RBAC-角色授权(RbacAccredit)Rest接口
 *
 * @author VX:PN15855012581
 * @since 2021-08-25 13:06:46
 */
@Api(tags = "RBAC-角色授权")
@RequestMapping("rbacAccredit")
public interface RbacAccreditRest<E, S extends IService> extends RbacAccreditApi {


}
