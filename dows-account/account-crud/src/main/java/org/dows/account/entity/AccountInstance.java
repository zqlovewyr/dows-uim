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
 * 账号-实例(AccountInstance)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountInstance对象", description = "账号-实例")
public class AccountInstance implements CrudEntity {
    private static final long serialVersionUID = 471265538286310949L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("账号密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("注册来源, 推广统计用")
    private String source;

    @ApiModelProperty("账号绑定的唯一手机号(可更换)")
    private String phone;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("账号区分：1、总控端2、总部端、3、门店端APP4、小程序流量用户")
    private Integer accountType;

    @ApiModelProperty("门店ID")
    private String storeId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

