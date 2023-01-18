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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID")
    private Long pid;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单CODE")
    private String menuCode;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("菜单路径URI")
    private String menuPath;

    @ApiModelProperty("菜单名称首字母")
    private String nameLetters;

    @ApiModelProperty("是否隐藏")
    private Integer visible;

    @ApiModelProperty("是否框架")
    private Integer isframe;

    @ApiModelProperty("应用名")
    private String appName;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty(value = "账号 id")
    private String accountId;
}

