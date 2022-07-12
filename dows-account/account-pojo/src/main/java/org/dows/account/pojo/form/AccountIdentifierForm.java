package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-标识(AccountIdentifier)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:25
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountIdentifier对象", description = "账号-标识")
public class AccountIdentifierForm implements Serializable {
    private static final long serialVersionUID = -83474980138621748L;

    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;
    @ApiModelProperty(value = "识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    @ApiModelProperty(value = "授权凭证【CREDENTIAL】：站内账号是密码、第三方登录是Token；")
    private String credential;
    @ApiModelProperty(value = "用户角色:ROLE_xxx 与 租户id: TENANT_XXX")
    private String authorities;
    @ApiModelProperty(value = "登录类型【IDENTITYTYPE】：登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；")
    private Integer loginType;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

