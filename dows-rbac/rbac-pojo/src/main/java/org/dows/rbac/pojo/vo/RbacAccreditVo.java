package org.dows.rbac.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-角色授权(RbacAccredit)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:40
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacAccredit对象", description = "RBAC-角色授权")
public class RbacAccreditVo implements Serializable {
    private static final long serialVersionUID = 103599975546785722L;

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


    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

}

