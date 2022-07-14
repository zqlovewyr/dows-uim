package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-授权客户端(AccountAuthClient)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:14
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountAuthClient对象", description = "账号-授权客户端")
public class AccountAuthClientForm implements Serializable {
    private static final long serialVersionUID = 594288440147812214L;

    @ApiModelProperty(value = "app secret")
    private String appSecret;
    @ApiModelProperty(value = "scope id 列表, 如: openid/userinfo/token/code/资源服务器标识等")
    private String scopes;
    @ApiModelProperty(value = "角色 id 列表, 通过逗号分割")
    private String roleIds;
    @ApiModelProperty(value = "客户端类型: web, 安卓, ios, 小程序…")
    private Integer clientType;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "应用ID")
    private String appId;
}

