package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordForm {
    @NotBlank(message = "原始密码不能为空")
    @ApiModelProperty("原始密码")
    private String orgPassword;
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String newPassword;
    @NotBlank(message = "确认密码不能为空")
    @ApiModelProperty("确认密码")
    private String confirmPassword;
}
