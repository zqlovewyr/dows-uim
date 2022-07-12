package org.dows.account.pojo.form;


import org.dows.framework.crud.api.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountTenantRelQuery extends PageQuery {
    @NotNull(message = "租户ID不可为空")
    private Long tenantId;
    private String accountName;

}
