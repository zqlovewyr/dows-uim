package org.dows.account.entity;

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
 * 账号标识(AccountIdentifier)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:52
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountIdentifier对象", description = "账号标识")
public class AccountIdentifier implements CrudEntity {
    private static final long serialVersionUID = -81984771141872564L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码")
    private String identifier;

    @ApiModelProperty("应用ID")
    private String appId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("")
    private Date dt;

}

