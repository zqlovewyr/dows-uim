package org.dows.account.pojo.form;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupRoleRelReq {
    private Long orgId;
    private String orgName;
    private String orgCode;

    private Long roleId;
    private Long tenantId;
    private List<Long> roleIdList;
}
