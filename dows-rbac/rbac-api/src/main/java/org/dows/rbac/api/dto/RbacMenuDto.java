package org.dows.rbac.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-菜单资源(RbacMenu)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:18
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacMenu对象", description = "RBAC-菜单资源")
public class RbacMenuDto implements Serializable {
    private static final long serialVersionUID = 167690930706915909L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号-菜单ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "权限码")
    private String resourceCode;
    @ApiModelProperty(value = "权限名称")
    private String resourceName;
    @ApiModelProperty(value = "资源名称首字母")
    private String resourceLetter;
    @ApiModelProperty(value = "权限值")
    private String resourceVal;
    @ApiModelProperty(value = "uri")
    private String resourcePath;
    @ApiModelProperty(value = "权限类型：2:菜单资源（目录/菜单）")
    private Integer resourceTyp;
    @ApiModelProperty(value = "子菜单数量")
    private Integer subCount;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "应用 id")
    private String appId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;


}
