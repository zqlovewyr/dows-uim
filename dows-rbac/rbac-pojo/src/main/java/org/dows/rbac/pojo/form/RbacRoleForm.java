package org.dows.rbac.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-角色(RbacRole)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:23
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacRole对象", description = "RBAC-角色")
public class RbacRoleForm implements Serializable {
    private static final long serialVersionUID = 811803774925586264L;

    @ApiModelProperty(value = "角色父id 角色组")
    private Long rolePid;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色code")
    private String roleCode;
    @ApiModelProperty(value = "名称首字母")
    private String nameLetter;
    @ApiModelProperty(value = "角色级别")
    private Integer roleLevel;
    @ApiModelProperty(value = "角色icon")
    private String roleIcon;
    @ApiModelProperty(value = "角色组数量")
    private Integer subCount;
    @ApiModelProperty(value = "数据权限")
    private String dataScope;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
    @ApiModelProperty(value = "描述")
    private String descr;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "应用ID")
    private String appId;
}

