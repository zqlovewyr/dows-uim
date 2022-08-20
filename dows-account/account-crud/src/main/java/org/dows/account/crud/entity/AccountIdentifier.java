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
    @ApiModelProperty("账号唯一标识如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("标识类型【IDENTITYTYPE】：0：系统账号、1：邮箱、2：手机")
    private Integer identifierType;
    @ApiModelProperty("状态，锁定/异常等 ")
    private Integer status;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
