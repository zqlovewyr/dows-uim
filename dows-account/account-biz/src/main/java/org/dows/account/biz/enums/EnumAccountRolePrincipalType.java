package org.dows.account.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumAccountRolePrincipalType implements StatusCode {
    PERSONAL(0, "个人"),
    GROUP(1, "组");
    final Integer code;
    final String descr;
}
