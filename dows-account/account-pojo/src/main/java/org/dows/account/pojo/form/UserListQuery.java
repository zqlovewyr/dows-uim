package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dows.framework.crud.api.PageQuery;

@Data
public class UserListQuery extends PageQuery {
    @ApiModelProperty("用户名/ID")
    private String accountName;
    private Integer status;
    private String phone;
    private String email;
    private String employeeNo;
    private String loginName;
    private Long tenantId;
    private Boolean isAllUser;
    private Long notTenantId;
}
