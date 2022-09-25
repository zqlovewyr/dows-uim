package org.dows.rbac.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC权限(RbacPrivilege)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:19
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacPrivilege对象", description = "RBAC权限")
public class RbacPrivilegeForm implements Serializable {
    private static final long serialVersionUID = 802191063783717621L;

    @ApiModelProperty(value = "权限ID（分布式id生成）")
    private String privilegeId;
    @ApiModelProperty(value = "权限名称")
    private String privilegeName;
    @ApiModelProperty(value = "权限码")
    private String privilegeCode;
    @ApiModelProperty(value = "权限类型（0：功能权限，1：数据权限）")
    private Integer privilegeTyp;
    @ApiModelProperty(value = "权限ICON")
    private String privilegeIcon;
    @ApiModelProperty(value = "资源ID")
    private Long resourceId;
    @ApiModelProperty(value = "资源父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "资源名")
    private String resourceName;
    @ApiModelProperty(value = "资源描述")
    private String resourceDescr;
    @ApiModelProperty(value = "资源类型")
    private Integer resourceTyp;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "是否缓存")
    private Boolean cache;
    @ApiModelProperty(value = "应用 id")
    private String appId;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
}

