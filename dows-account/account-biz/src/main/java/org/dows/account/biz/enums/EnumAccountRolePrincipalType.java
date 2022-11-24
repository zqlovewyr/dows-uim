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
    PERSONAL(400101, "个人"),
    GROUP(400102, "组");
    final Integer code;
    final String descr;
}
