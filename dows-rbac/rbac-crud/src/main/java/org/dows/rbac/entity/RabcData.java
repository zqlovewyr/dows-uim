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
 * RBAC-数据资源(RabcData)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:03
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RabcData对象", description = "RBAC-数据资源")
public class RabcData implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID(数据库自增)")
    private Long id;
    @ApiModelProperty("资源ID(分布式id)")
    private String resourceId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("资源父id")
    private Long resourcePid;
    @ApiModelProperty("资源名称")
    private String resouceName;
    @ApiModelProperty("资源code")
    private String resourceCode;
    @ApiModelProperty("资源描述")
    private String resourceDescr;
    @ApiModelProperty("资源类型：3:数据资源")
    private Integer resourceTyp;
    @ApiModelProperty("资源属性（json）")
    private String properties;
    @ApiModelProperty("应用id")
    private String appId;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
