package org.dows.account.pojo.form;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleRelReq {
    private Long accountId;
    private Long tenantId;
    private Long roleId;
    private List<Long> roleIdList;
}
