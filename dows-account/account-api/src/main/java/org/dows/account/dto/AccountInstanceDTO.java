package org.dows.account.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private String password;
    private String avatar;
    private String source;
    private String phone;
}
