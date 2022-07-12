package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 账号-授权客户端(AccountAuthClient)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:21
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountAuthClient对象", description = "账号-授权客户端")
public class AccountAuthClient implements CrudEntity {
    @ApiModelProperty("授权客户端ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("app secret")
    private String appSecret;
    @ApiModelProperty("scope id 列表, 如: openid/userinfo/token/code/资源服务器标识等")
    private String scopes;
    @ApiModelProperty("角色 id 列表, 通过逗号分割")
    private String roleIds;
    @ApiModelProperty("客户端类型: web, 安卓, ios, 小程序…")
    private Integer clientType;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
