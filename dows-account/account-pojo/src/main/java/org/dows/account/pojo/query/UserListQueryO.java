package org.dows.account.pojo.query;


import org.dows.framework.api.EnumStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserListQueryO {
    @ApiModelProperty("用户名")
    private String userNameLike;
    private EnumStatus status;
    private String phone;
    private String email;
    private String employeeNo;
    private String loginName;
}
