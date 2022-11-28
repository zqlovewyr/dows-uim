package org.dows.account.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dows.framework.api.StatusCode;

/**
 * @author runsix
 */
@Getter
@AllArgsConstructor
public enum EnumAccountStatusCode implements StatusCode {
    ACCOUNT_EXIST_EXCEPTION(400001, "账号已存在"),
    BATCH_IMPORT_IDENTIFIER_DUPLICATED(400002, "批量导入数据账户有重复"),
    ACCOUNT_INSTANCE_DTO_CANNOT_BE_NULL(400003, "账户实例不能为空"),
    APPID_CANNOT_BE_BLANK(400004, "应用ID不能为空"),
    IDENTIFIER_CANNOT_BE_BLANK(400004, "账号不能为空"),
    ACCOUNT_NAME_CANNOT_BE_BLANK(400004, "账号昵称不能为空"),
    ORG_NOT_EXIST_EXCEPTION(400201, "组织不存在");
    final Integer code;
    final String descr;
}
