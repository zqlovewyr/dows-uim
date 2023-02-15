package org.dows.account.biz.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInstanceDTO implements Serializable {
    private static final long serialVersionUID = 4496765086642042824L;
    private String appId;
    /* runsix:field 'identifier' is in class AccountIdentifier. */
    @ExcelProperty(value = "用户账号(code)")
    private String identifier;
    /* runsix:field 'rbacRoleId' is field 'id' in class RbacRole */
    private Long rbacRoleId;
    /* runsix:field 'accountOrgId' is field 'orgId' in class AccountOrg */
    private String accountOrgOrgId;
    @ExcelProperty(value = "用户姓名")
    private String accountName;
    @ExcelProperty(value = "密码")
    private String password;
    private String avatar;
    @ExcelProperty(value = "来源")
    private String source;
    @ExcelProperty(value = "手机号")
    private String phone;
    @ExcelProperty(value = "账号类型 账号区分：1、总控端2、总部端、3、门店端APP4、小程序流量用户")
    private Integer accountType;

    @ExcelProperty(value = "门店ID")
    private String storeId;
}
