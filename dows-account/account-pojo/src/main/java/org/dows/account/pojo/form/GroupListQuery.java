package org.dows.account.pojo.form;


import org.dows.framework.crud.api.PageQuery;
import lombok.Data;

@Data
public class GroupListQuery extends PageQuery {
    private String orgName;
    private Integer status;
    private Long tenantId;
}
