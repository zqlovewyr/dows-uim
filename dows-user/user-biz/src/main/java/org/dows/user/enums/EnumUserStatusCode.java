package org.dows.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumUserStatusCode implements StatusCode {
    USER_CREATE_FAIL_EXCEPTION(500001, "创建用户失败");
    final Integer code;
    final String descr;
}
