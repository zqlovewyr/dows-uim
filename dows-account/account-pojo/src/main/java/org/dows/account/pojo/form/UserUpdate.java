package org.dows.account.pojo.form;

import org.dows.framework.validate.IsPhone;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdate {
    @NotNull(message = "用户名不可为空")
    @ApiModelProperty("用户名")
    private String accountName;

    private String employeeNo;
    //@NotNull(message = "手机号不可为空")
    @IsPhone
    @ApiModelProperty("手机号")
    private String phone;
    @Email(message = "邮箱格式错误")
    private String email;

    //@Min(value = 5,message = "登录名最小长度为5")
    // @Max(value = 36,message = "登录名最长长度为36")
    private String loginName;
    private String password;
    private String confirmPassword;
    private Long id;
}
