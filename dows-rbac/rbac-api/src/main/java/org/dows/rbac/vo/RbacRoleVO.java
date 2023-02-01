package org.dows.rbac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RbacRoleVO implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
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

    @ApiModelProperty("角色状态")
    private Integer status;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("当前角色是否继承父角色对应的权限")
    private Boolean inherit;

    @JsonIgnore
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;
}
