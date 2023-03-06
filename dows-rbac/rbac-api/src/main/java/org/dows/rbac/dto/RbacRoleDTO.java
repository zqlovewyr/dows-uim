package org.dows.rbac.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @description: RBAC-角色(RbacRole)DTO类
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
public class RbacRoleDTO implements Serializable {
    private static final long serialVersionUID = -11320090997139310L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号角色ID")
    private Long id;

    @ApiModelProperty(value = "角色父id 角色组")
    private Long rolePid;

    @ApiModelProperty(value = "账号角色ID集合")
    private Set<Long> ids;

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

    @ApiModelProperty("角色状态")
    private Integer status;

    @ApiModelProperty(value = "角色组数量")
    private Integer subCount;

    @ApiModelProperty(value = "数据权限")
    private String dataScope;

    @ApiModelProperty(value = "顺序")
    private Integer sorted;

    @ApiModelProperty(value = "描述")
    private String descr;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
