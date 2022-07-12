package org.dows.account.pojo.form;

import org.dows.framework.crud.api.PageQuery;
import lombok.Data;

@Data
public class GroupUserQuery extends PageQuery {
    private Long groupId;
    private Long tenantId;
}
