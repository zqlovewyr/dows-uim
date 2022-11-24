package org.dows.account.biz.dto;

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
    /* runsix:field 'identifier' is in class AccountIdentifier. */
    private String identifier;
    /* runsix:field 'rbacRoleId' is field 'id' in class RbacRole */
    private Long rbacRoleId;
    /* runsix:field 'accountOrgId' is field 'orgId' in class AccountOrg */
    private String accountOrgOrgId;
    private String accountName;
    private String password;
    private String avatar;
    private String source;
    private String phone;
    private String appId;
}
