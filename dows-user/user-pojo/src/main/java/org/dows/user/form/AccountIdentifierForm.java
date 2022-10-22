package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号标识(AccountIdentifier)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountIdentifierForm 表单对象", description = "账号标识")
public class AccountIdentifierForm implements Serializable {
    private static final long serialVersionUID = 945033652602304495L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码")
    private String identifier;

    @ApiModelProperty("应用ID")
    private String appId;

    @JsonIgnore
    private Boolean deleted;


}

