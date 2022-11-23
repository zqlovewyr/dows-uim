package org.dows.account.biz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumAccountStatusCode implements StatusCode {
    ACCOUNT_EXIST_EXCEPTION(400001, "账号已存在");
    final Integer code;
    final String descr;
}
