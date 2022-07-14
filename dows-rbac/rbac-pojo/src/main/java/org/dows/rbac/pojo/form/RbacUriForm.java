package org.dows.rbac.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-功能资源(RbacUri)Form类
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
public class RbacUriForm implements Serializable {
    private static final long serialVersionUID = -35568116251980103L;

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
}

