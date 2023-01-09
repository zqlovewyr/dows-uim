package org.dows.tenant.form;

import java.util.Date;

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

/**
 * 租户-实例(TenantInstance)实体类
 *
 * @author lait
 * @since 2023-01-08 16:35:30
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TenantInstance对象", description = "租户-实例")
public class TenantInstanceForm {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库主键ID")
    private Long id;


    @ApiModelProperty("租户id")
    private String tenantId;


    @ApiModelProperty("租户名称")
    private String tenantName;


    @ApiModelProperty("租户code")
    private String tenantCode;


    @ApiModelProperty("租户ICON")
    private String tenantIcon;


    @ApiModelProperty("描述")
    private String descr;


    @ApiModelProperty("状态（0:启用，1:禁用）")
    private Integer status;


}

