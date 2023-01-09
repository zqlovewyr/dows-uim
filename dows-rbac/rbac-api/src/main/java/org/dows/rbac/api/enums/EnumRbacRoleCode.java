package org.dows.rbac.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author：wHuan
 * @Date：2022/11/28 14:53
 */
@Getter
@AllArgsConstructor
public enum EnumRbacRoleCode {

    // 管理角色 adminRole
    RBAC_ROLE_ADMIN_CODE("ADMIN", "管理员"),
    // 教师角色 teacherRole
    RBAC_ROLE_TEACHER_CODE("TEACHER", "教师"),
    // 学生角色 studentRole
    RBAC_ROLE_STUDENT_CODE("STUDENT", "学生");

    final String code;
    final String name;

    public static int sortValue(String code) {
        for (EnumRbacRoleCode enumCode : EnumRbacRoleCode.values()) {
            if (enumCode.getCode().equals(code)) {
                return enumCode.ordinal();
            }
        }
        return -1;
    }


}
