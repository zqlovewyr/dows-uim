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
    USER_CREATE_FAIL_EXCEPTION(500001, "创建用户失败"),
    USER_IS_NOT_EXIST_EXCEPTION(500002, "该用户不存在"),
    USER_FAMILY_CREATE_FAIL_EXCEPTION(500003, "创建家庭失败");
    final Integer code;
    final String descr;
}
