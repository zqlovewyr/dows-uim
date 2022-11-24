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
 * rbac-菜单(RbacMenu)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:45
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacMenu对象", description = "rbac-菜单")
public class RbacMenu implements CrudEntity {
    private static final long serialVersionUID = -82027952009405842L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("账号-菜单ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID")
    private Long pid;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单CODE")
    private String menuCode;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("菜单路径URI")
    private String menuPath;

    @ApiModelProperty("菜单名称首字母")
    private String nameLetters;

    @ApiModelProperty("是否隐藏")
    private Boolean visible;

    @ApiModelProperty("是否框架")
    private Boolean isframe;

    @ApiModelProperty("应用名")
    private String appName;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

