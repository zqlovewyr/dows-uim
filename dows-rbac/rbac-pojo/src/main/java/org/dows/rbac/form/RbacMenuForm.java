package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-菜单(RbacMenu)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:17
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacMenuForm 表单对象", description = "rbac-菜单")
public class RbacMenuForm implements Serializable {
    private static final long serialVersionUID = -35423906410730200L;
    @JsonIgnore
    private Long id;

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
    private Boolean deleted;


}

