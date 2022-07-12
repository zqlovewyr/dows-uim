package org.dows.account.pojo.form;

import lombok.Data;

import java.util.List;

@Data
public class GroupAddAccountReq {
    private Long orgId;
    private List<Long> accountIdList;
}
