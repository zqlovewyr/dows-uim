package org.dows.account.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListQueryO {
    @ApiModelProperty("用户名")
    private String userNameLike;
    private String phone;
    private String email;
    private String employeeNo;
    private String loginName;
}
