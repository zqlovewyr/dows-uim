package org.dows.rbac.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-应用资源(RbacResource)Query类
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
@ApiModel(value = "RbacResource对象", description = "RBAC-应用资源")
public class RbacResourceQuery implements Serializable {
    private static final long serialVersionUID = 890763389677284617L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "主键ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "资源父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "资源名")
    private String resourceName;
    @ApiModelProperty(value = "资源码")
    private String resourceCode;
    @ApiModelProperty(value = "类型: 1目录, 2菜单, 3按钮, 4链接")
    private Integer resourceTyp;
    @ApiModelProperty(value = "资源值")
    private String resourceVal;
    @ApiModelProperty(value = "资源路径")
    private String resourcePath;
    @ApiModelProperty(value = "资源图标")
    private String resourceIcon;
    @ApiModelProperty(value = "资源描述")
    private String resourceDescr;
    @ApiModelProperty(value = "是否隐藏: 0不隐藏, 1隐藏. 默认: 0")
    private Boolean visible;
    @ApiModelProperty(value = "层级")
    private Integer level;
    @ApiModelProperty(value = "应用 id")
    private String appId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
}

