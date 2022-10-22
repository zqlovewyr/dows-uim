package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-权限(RbacPermission)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacPermissionForm 表单对象", description = "rbac-权限")
public class RbacPermissionForm implements Serializable {
    private static final long serialVersionUID = -37578315517994842L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("父角色ID(继承时该字段有值)")
    private Long rolePid;

    @ApiModelProperty("资源ID")
    private Long uriId;

    @ApiModelProperty("角色CODE")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("权限码")
    private String authority;

    @ApiModelProperty("uri路径")
    private String uriPath;

    @ApiModelProperty("功能描述")
    private String uriDescr;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("是否隐藏: 0不隐藏, 1隐藏. 默认: 0")
    private Boolean visible;

    @JsonIgnore
    private Boolean deleted;


}

