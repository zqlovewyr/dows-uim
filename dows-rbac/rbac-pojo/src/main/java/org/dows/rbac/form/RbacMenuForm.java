package org.dows.rbac.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-菜单资源(RbacMenu)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:18
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacMenu对象", description = "RBAC-菜单资源")
public class RbacMenuForm implements Serializable {
    private static final long serialVersionUID = 199327959683880485L;

    @ApiModelProperty(value = "父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "权限码")
    private String resourceCode;
    @ApiModelProperty(value = "权限名称")
    private String resourceName;
    @ApiModelProperty(value = "资源名称首字母")
    private String resourceLetter;
    @ApiModelProperty(value = "权限值")
    private String resourceVal;
    @ApiModelProperty(value = "uri")
    private String resourcePath;
    @ApiModelProperty(value = "权限类型：2:菜单资源（目录/菜单）")
    private Integer resourceTyp;
    @ApiModelProperty(value = "子菜单数量")
    private Integer subCount;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "应用 id")
    private String appId;
}

