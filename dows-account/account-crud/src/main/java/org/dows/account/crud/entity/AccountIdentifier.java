package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @since 2022-07-14 21:33:24
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库自增ID")
    private Long id;
    @ApiModelProperty("账号ID/用户ID/会员ID/商户ID")
    private String accountId;
    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    @ApiModelProperty("授权凭证【CREDENTIAL】：站内账号是密码、第三方登录是Token；")
    private String credential;
    @ApiModelProperty("标识类型：登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；")
    private Integer identifierType;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("")
    private String source;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
