package org.dows.rbac.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * RBAC-应用资源(RbacResource)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-15 12:31:06
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacResource对象", description = "RBAC-应用资源")
public class RbacResource implements CrudEntity {
    @ApiModelProperty("主键ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("资源父ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long resourcePid;
    @ApiModelProperty("资源名")
    private String resourceName;
    @ApiModelProperty("资源码")
    private String resourceCode;
    @ApiModelProperty("类型: 1目录, 2菜单, 3按钮, 4链接")
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
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("资源名称首字母")
    private String resourceLetter;

}
