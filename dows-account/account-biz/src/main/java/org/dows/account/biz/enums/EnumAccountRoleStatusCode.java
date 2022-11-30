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
    ACCOUNT_ROLE_NOT_EXIST_EXCEPTION(400100, "账号不存在任何可用角色");
    final Integer code;
    final String descr;

}
