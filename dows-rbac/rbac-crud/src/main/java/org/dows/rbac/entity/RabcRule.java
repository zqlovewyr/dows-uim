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
 * RBAC-权限规则(RabcRule)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:07
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RabcRule对象", description = "RBAC-权限规则")
public class RabcRule implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID(数据库自增)")
    private Long id;
    @ApiModelProperty("规则分布式id")
    private String ruleId;
    @ApiModelProperty("权限规则名称")
    private String ruleName;
    @ApiModelProperty("权限规则code")
    private String ruleCode;
    @ApiModelProperty("资源ID(分布式id)")
    private String resourceId;
    @ApiModelProperty("资源类型")
    private Integer resourceTyp;
    @ApiModelProperty("规则对应的表达式(json)")
    private String expr;
    @ApiModelProperty("应用id")
    private String appId;
    @ApiModelProperty("租户id")
    private String tenantId;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
