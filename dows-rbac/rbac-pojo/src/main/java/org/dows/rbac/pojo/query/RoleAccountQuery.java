package org.dows.rbac.pojo.query;


import lombok.Data;
import org.dows.framework.crud.api.PageQuery;

@Data
public class RoleAccountQuery extends PageQuery {
    private Long roleId;
}
