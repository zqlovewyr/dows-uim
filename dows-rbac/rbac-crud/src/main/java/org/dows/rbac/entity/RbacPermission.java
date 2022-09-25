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
 * RBAC权限资源(RbacPermission)表实体类
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
@ApiModel(value = "RbacPermission对象", description = "RBAC权限资源")
public class RbacPermission implements CrudEntity {
    @ApiModelProperty("主键ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("权限名称")
    private String permissionName;
    @ApiModelProperty("权限码")
    private String permissionCode;
    @ApiModelProperty("权限ICON")
    private String permissionIcon;
    @ApiModelProperty("资源ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long resourceId;
    @ApiModelProperty("资源父ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long resourcePid;
    @ApiModelProperty("资源名")
    private String resourceName;
    @ApiModelProperty("类型：0=目录，1=菜单，2=按钮")
    private Integer resourceTyp;
    @ApiModelProperty("资源值")
    private String resourceVal;
    @ApiModelProperty("资源路径")
    private String resourcePath;
    @ApiModelProperty("资源图标")
    private String resourceIcon;
    @ApiModelProperty("资源描述")
    private String resourceDescr;
    @ApiModelProperty("租户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tenantId;
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
    @ApiModelProperty("组件名称")
    private String componentName;
    @ApiModelProperty("组件")
    private String component;
    @ApiModelProperty("排序")
    private Integer sorted;
    @ApiModelProperty("子菜单数目")
    private Integer subCount;
    @ApiModelProperty("是否外链")
    private Object iframe;
    @ApiModelProperty("是否缓存")
    private Object cache;
    @ApiModelProperty("是否显示")
    private Object hidden;
    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("产品类型ID")
    private Long categoryId;

    private Integer isPlatform;
}
