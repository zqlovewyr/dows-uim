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
    USER_FAMILY_CREATE_FAIL_EXCEPTION(500003, "创建家庭失败"),
    USER_FAMILY_UPDATE_FAIL_EXCEPTION(500004, "更新家庭失败"),
    USER_EXTINFO_CREATE_FAIL_EXCEPTION(500005, "创建用户扩展信息失败"),
    USER_COMPANY_CREATE_FAIL_EXCEPTION(500006, "创建用户公司信息失败"),
    USER_EDUCATION_CREATE_FAIL_EXCEPTION(500007, "创建用户教育信息失败"),
    USER_JOB_CREATE_FAIL_EXCEPTION(500008, "创建用户工作信息失败"),
    USER_DWELLING_CREATE_FAIL_EXCEPTION(500009, "创建用户住所信息失败");
    final Integer code;
    final String descr;
}
