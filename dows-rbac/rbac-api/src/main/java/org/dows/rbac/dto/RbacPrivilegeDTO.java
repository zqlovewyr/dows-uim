package org.dows.rbac.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC权限(RbacPrivilege)DTO类
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
public class RbacPrivilegeDTO implements Serializable {
    private static final long serialVersionUID = -28307812421685486L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键ID(数据库自增)")
    private Long id;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "资源ID")
    private Long resourceId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;


}
