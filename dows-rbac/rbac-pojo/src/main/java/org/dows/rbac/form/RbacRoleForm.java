package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-角色(RbacRole)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:19
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacRoleForm 表单对象", description = "rbac-角色")
public class RbacRoleForm implements Serializable {
    private static final long serialVersionUID = 733471903025967637L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("角色父ID(角色组|继承)")
    private Long pid;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色名称首字母,方便查找")
    private String nameLetters;

    @ApiModelProperty("角色code")
    private String roleCode;

    @ApiModelProperty("角色icon")
    private String roleIcon;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("当前角色是否继承父角色对应的权限")
    private Boolean inherit;

    @JsonIgnore
    private Boolean deleted;


}

