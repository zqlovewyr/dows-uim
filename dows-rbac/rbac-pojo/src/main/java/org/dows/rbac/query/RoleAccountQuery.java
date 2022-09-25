package org.dows.rbac.query;


import lombok.Data;
import org.dows.framework.crud.api.PageQuery;

@Data
public class RoleAccountQuery extends PageQuery {
    private Long roleId;
}
