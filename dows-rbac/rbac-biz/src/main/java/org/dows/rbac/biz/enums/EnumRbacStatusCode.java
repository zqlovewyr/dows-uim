package org.dows.rbac.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumRbacStatusCode implements StatusCode {
    RBAC_ROLE_NOT_EXIST_EXCEPTION(500001, "角色不存在");
    final Integer code;
    final String descr;
}
