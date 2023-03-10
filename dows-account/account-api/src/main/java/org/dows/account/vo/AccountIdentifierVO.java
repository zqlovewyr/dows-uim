package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountIdentifierVO implements Serializable {
    private static final long serialVersionUID = -8620453797472478983L;
    @ApiModelProperty("账号ID")
    private String accountId;
    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码")
    private String identifier;
    @ApiModelProperty("应用ID")
    private String appId;
}
