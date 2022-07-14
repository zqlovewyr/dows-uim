package org.dows.rbac.crud.entity;

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
 * RBAC-菜单资源(RbacMenu)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:17
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacMenu对象", description = "RBAC-菜单资源")
public class RbacMenu implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("账号-菜单ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID")
    private Long resourcePid;
    @ApiModelProperty("权限码")
    private String resourceCode;
    @ApiModelProperty("权限名称")
    private String resourceName;
    @ApiModelProperty("资源名称首字母")
    private String resourceLetter;
    @ApiModelProperty("权限值")
    private String resourceVal;
    @ApiModelProperty("uri")
    private String resourcePath;
    @ApiModelProperty("权限类型：2:菜单资源（目录/菜单）")
    private Integer resourceTyp;
    @ApiModelProperty("子菜单数量")
    private Integer subCount;
    @ApiModelProperty("排序")
    private Integer sorted;
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
