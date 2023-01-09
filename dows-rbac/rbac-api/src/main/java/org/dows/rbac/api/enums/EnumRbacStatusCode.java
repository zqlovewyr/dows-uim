package org.dows.rbac.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumRbacStatusCode implements StatusCode {
    RBAC_ROLE_NOT_EXIST_EXCEPTION(500001, "角色不存在"),
    RBAC_ROLE_NOT_PERMISSION_EXCEPTION(500100, "角色无权限访问");
    final Integer code;
    final String descr;
}
