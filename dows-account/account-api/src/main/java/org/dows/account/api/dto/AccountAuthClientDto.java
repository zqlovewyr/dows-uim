package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-授权客户端(AccountAuthClient)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountAuthClient对象", description = "账号-授权客户端")
public class AccountAuthClientDto implements Serializable {
    private static final long serialVersionUID = 762688265008705512L;

    /**
     * 授权客户端ID
     */
    @ApiModelProperty(value = "授权客户端ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * app secret
     */
    @ApiModelProperty(value = "app secret")
    private String appSecret;
    /**
     * scope id 列表, 如: openid/userinfo/token/code/资源服务器标识等
     */
    @ApiModelProperty(value = "scope id 列表, 如: openid/userinfo/token/code/资源服务器标识等")
    private String scopes;
    /**
     * 角色 id 列表, 通过逗号分割
     */
    @ApiModelProperty(value = "角色 id 列表, 通过逗号分割")
    private String roleIds;
    /**
     * 客户端类型: web, 安卓, ios, 小程序…
     */
    @ApiModelProperty(value = "客户端类型: web, 安卓, ios, 小程序…")
    private Integer clientType;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    private String appId;
    /**
     * 乐观锁, 默认: 0
     */
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    /**
     * 时间戳/创建时间
     */
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    /**
     * 是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
     */
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
