package org.dows.rbac.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-菜单资源(RbacMenu)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:53
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacMenu对象", description = "RBAC-菜单资源")
public class RbacMenuQuery implements Serializable {
    private static final long serialVersionUID = 968226294652381260L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-菜单ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "权限码")
    private String resourceCode;
    @ApiModelProperty(value = "权限名称")
    private String resourceName;
    @ApiModelProperty(value = "权限值")
    private String resourceVal;
    @ApiModelProperty(value = "uri")
    private String resourcePath;
    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）, 4->链接")
    private Integer resourceTyp;
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

