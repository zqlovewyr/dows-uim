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
 * RBAC-功能资源(RbacUri)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:24
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacUri对象", description = "RBAC-功能资源")
public class RbacUri implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("资源父ID")
    private Long resourcePid;
    @ApiModelProperty("资源名")
    private String resourceName;
    @ApiModelProperty("资源名称首字母")
    private String resourceLetter;
    @ApiModelProperty("资源码")
    private String resourceCode;
    @ApiModelProperty("类型: 1:功能资源")
    private Integer resourceTyp;
    @ApiModelProperty("资源值")
    private String resourceVal;
    @ApiModelProperty("资源路径")
    private String resourcePath;
    @ApiModelProperty("资源图标")
    private String resourceIcon;
    @ApiModelProperty("资源描述")
    private String resourceDescr;
    @ApiModelProperty("是否隐藏: 0不隐藏, 1隐藏. 默认: 0")
    private Boolean visible;
    @ApiModelProperty("层级")
    private Integer level;
    @ApiModelProperty("应用 id")
    private String appId;
    @JsonIgnore
    private Integer ver;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
