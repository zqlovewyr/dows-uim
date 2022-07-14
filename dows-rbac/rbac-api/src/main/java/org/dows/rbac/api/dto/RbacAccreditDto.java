package org.dows.rbac.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-角色授权(RbacAccredit)DTO类
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
public class RbacAccreditDto implements Serializable {
    private static final long serialVersionUID = -71358400945590161L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号角色ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "角色父id 角色组")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色code")
    private String roleCode;
    @ApiModelProperty(value = "角色icon")
    private String roleIcon;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "应用 id")
    private String appId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;


}
