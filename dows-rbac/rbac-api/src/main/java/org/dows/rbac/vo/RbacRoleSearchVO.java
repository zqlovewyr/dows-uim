package org.dows.rbac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rbac.SearchRequest;

import java.io.Serializable;
import java.util.Set;

/**
 * rbac 权限搜索请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RbacRoleSearchVO implements SearchRequest, Serializable {
    private static final long serialVersionUID = 7347577135380075234L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("角色父ID(角色组|继承)")
    private Long pid;
    @ApiModelProperty("角色名")
    private String roleName;
    @ApiModelProperty("角色名称首字母,方便查找")
    private String nameLetters;
    @ApiModelProperty("角色code")
    private String roleCode;
    @ApiModelProperty("角色状态")
    private Integer status;
    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("当前角色是否继承父角色对应的权限")
    private Boolean inherit;
    /*特殊需要的参数*/
    @ApiModelProperty("RbacRole 表内数据库ID 集合")
    private Set<String> ids;
}
