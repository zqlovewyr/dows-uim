package org.dows.rbac.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-角色授权(RbacAccredit)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:52
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacAccredit对象", description = "RBAC-角色授权")
public class RbacAccreditQuery implements Serializable {
    private static final long serialVersionUID = 958198145952316730L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号角色ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "角色父id 角色组")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色code")
    private String roleCode;
    @ApiModelProperty(value = "角色icon")
    private String roleIcon;
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;
    @ApiModelProperty(value = "权限名称")
    private String permissionName;
    @ApiModelProperty(value = "权限码")
    private String permissionCode;
    @ApiModelProperty(value = "权限icon")
    private String permissionIcon;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
    @ApiModelProperty(value = "描述")
    private String descr;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "应用 id")
    private String appId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted = false;

}

