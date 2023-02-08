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
    USER_DWELLING_CREATE_FAIL_EXCEPTION(500009, "创建用户住所信息失败"),
    USER_EXTINFO_UPDATE_FAIL_EXCEPTION(500010, "更新用户扩展信息失败"),
    USER_JOB_UPDATE_FAIL_EXCEPTION(500011, "更新用户工作信息失败"),
    USER_COMPANY_UPDATE_FAIL_EXCEPTION(500012, "更新用户公司信息失败"),
    USER_EDUCATION_UPDATE_FAIL_EXCEPTION(500013, "更新用户教育信息失败"),
    USER_FAMILY_IS_NOT_EXIST_EXCEPTION(500014, "该用户家庭关系不存在"),
    USER_EXTINFO_IS_NOT_EXIST_EXCEPTION(500015, "该用户扩展信息不存在"),
    USER_JOB_IS_NOT_EXIST_EXCEPTION(500016, "该用户工作信息不存在"),
    USER_COMPANY_IS_NOT_EXIST_EXCEPTION(500017, "该用户公司信息不存在"),
    USER_EDUCATION_IS_NOT_EXIST_EXCEPTION(500017, "该用户公司信息不存在");
    final Integer code;
    final String descr;
}
