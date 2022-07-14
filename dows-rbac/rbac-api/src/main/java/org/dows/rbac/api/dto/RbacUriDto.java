package org.dows.rbac.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-功能资源(RbacUri)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:25
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacUri对象", description = "RBAC-功能资源")
public class RbacUriDto implements Serializable {
    private static final long serialVersionUID = 288702958853349463L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "资源父ID")
    private Long resourcePid;
    @ApiModelProperty(value = "资源名")
    private String resourceName;
    @ApiModelProperty(value = "资源名称首字母")
    private String resourceLetter;
    @ApiModelProperty(value = "资源码")
    private String resourceCode;
    @ApiModelProperty(value = "类型: 1:功能资源")
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
