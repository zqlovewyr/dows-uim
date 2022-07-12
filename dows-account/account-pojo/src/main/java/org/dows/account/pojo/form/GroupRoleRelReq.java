package org.dows.account.pojo.form;

import lombok.Data;

import java.util.List;

@Data
public class GroupRoleRelReq {
    private Long orgId;
    private List<Long> roleIdList;
}
