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
 * 账号-标识(AccountIdentifier)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:24
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountIdentifier对象", description = "账号-标识")
public class AccountIdentifier implements CrudEntity {
    @ApiModelProperty("AccountIdentifier ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("账号ID/用户ID/会员ID/商户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    @ApiModelProperty("授权凭证【CREDENTIAL】：站内账号是密码、第三方登录是Token；")
    private String credential;
    @ApiModelProperty("用户角色:ROLE_xxx 与 租户id: TENANT_XXX")
    private String authorities;
    @ApiModelProperty("登录类型【IDENTITYTYPE】：登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；")
    private Integer loginType;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}
