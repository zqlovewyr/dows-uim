package org.dows.account.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * 账号-角色 异常code
 * @Author：wHuan
 * @Date：2022/11/28 15:19
 */
@Getter
@AllArgsConstructor
public enum EnumAccountRoleStatusCode implements StatusCode {

    // 未存在角色
    ACCOUNT_ROLE_NOT_EXIST_EXCEPTION(500001, "账号不存在任何可用角色"),
    ACCOUNT_ROLE_AUTH_FAIl_EXCEPTION(500002, "账号授权失败");
    final Integer code;
    final String descr;

}
