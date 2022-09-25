package org.dows.account.query;

import org.dows.framework.crud.api.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountOwnerQuery extends PageQuery {
    @ApiModelProperty("账号ID")
    private Long accountId;

    private Long tenantId;
}
