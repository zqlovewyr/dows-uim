package org.dows.rbac.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-角色授权(RbacAccredit)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:12
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacAccredit对象", description = "RBAC-角色授权")
public class RbacAccreditForm implements Serializable {
    private static final long serialVersionUID = -34535437120388635L;

    @ApiModelProperty(value = "角色父id 角色组")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色code")
    private String roleCode;
    @ApiModelProperty(value = "角色icon")
    private String roleIcon;
    @ApiModelProperty(value = "权限ID")
    private Long privilegeId;
    @ApiModelProperty(value = "权限名称")
    private String privilegeName;
    @ApiModelProperty(value = "权限码")
    private String privilegeCode;
    @ApiModelProperty(value = "权限icon")
    private String privilegeIcon;
    private Integer privilegeTyp;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
    @ApiModelProperty(value = "描述")
    private String descr;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "应用 id")
    private String appId;
}

