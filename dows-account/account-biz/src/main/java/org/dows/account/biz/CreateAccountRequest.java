package org.dows.account.biz;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码")
    private String identifier;

    @ApiModelProperty("应用ID")
    private String appId;


}
