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
    ACCOUNT_ORG_IS_NOT_EXIST(400004, "组织架构不存在"),
    APPID_CANNOT_BE_BLANK(400005, "应用ID不能为空"),
    IDENTIFIER_CANNOT_BE_BLANK(400006, "账号不能为空"),
    ACCOUNT_NAME_CANNOT_BE_BLANK(400007, "账号昵称不能为空"),
    ORG_NOT_EXIST_EXCEPTION(400008, "组织不存在"),
    ACCOUNT_NOT_EXIST_EXCEPTION(400009, "账号不存在"),
    ACCOUNT_STATUS_INVALID_EXCEPTION(400010, "账号已失效"),
    ACCOUNT_NOT_IN_VALIDITY_EXCEPTION(400011, "账号不在有效期"),
    ACCOUNT_PASSWORD_NOT_MATCH_EXCEPTION(400012, "账号与密码不匹配");
    final Integer code;
    final String descr;
}
