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
 * 租户-应用(TenantApp)实体类
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
@ApiModel(value = "TenantApp对象", description = "租户-应用")
public class TenantAppForm {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("账号角色ID")
    private Long id;


    @ApiModelProperty("租户ID")
    private String tenantId;


    @ApiModelProperty("租户名称")
    private String tenantName;


    @ApiModelProperty("商户号")
    private String merchantNo;


    @ApiModelProperty("应用ID")
    private String appId;


    @ApiModelProperty("应用名称")
    private String appName;


    @ApiModelProperty("应用码")
    private String appCode;


    @ApiModelProperty("应用icon")
    private String appIcon;


    @ApiModelProperty("顺序")
    private Integer sorted;


    @ApiModelProperty("描述")
    private String descr;


    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;


}

