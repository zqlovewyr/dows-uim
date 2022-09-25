package org.dows.rbac.entity;

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
 * rbac-权限表达式(RbacExpr)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:14
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacExpr对象", description = "rbac-权限表达式")
public class RbacExpr implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键")
    private Long id;
    @ApiModelProperty("分布式主键")
    private String exprId;
    @ApiModelProperty("键")
    private String key;
    @ApiModelProperty("条件(> = < in等)")
    private String criteria;
    @ApiModelProperty("值")
    private String val;
    @ApiModelProperty("逻辑（and or）")
    private String logic;
    @ApiModelProperty("应用id")
    private String appId;
    @ApiModelProperty("租户号")
    private String tenantId;
    @ApiModelProperty("")
    private String descr;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
