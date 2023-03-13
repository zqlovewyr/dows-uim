package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户-登录名
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountIdentifierVO implements Serializable {
    private static final long serialVersionUID = -8620453797472478983L;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty(value = "AccountIdentifier ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private String accountId;
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
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
