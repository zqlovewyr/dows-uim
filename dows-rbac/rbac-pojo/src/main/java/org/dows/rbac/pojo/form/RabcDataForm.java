package org.dows.rbac.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-数据资源(RabcData)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:05
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RabcData对象", description = "RBAC-数据资源")
public class RabcDataForm implements Serializable {
    private static final long serialVersionUID = 640373802640973154L;

    @ApiModelProperty(value = "资源ID(分布式id)")
    private String resourceId;
    @ApiModelProperty(value = "资源父id")
    private Long resourcePid;
    @ApiModelProperty(value = "资源名称")
    private String resouceName;
    @ApiModelProperty(value = "资源code")
    private String resourceCode;
    @ApiModelProperty(value = "资源描述")
    private String resourceDescr;
    @ApiModelProperty(value = "资源类型：3:数据资源")
    private Integer resourceTyp;
    @ApiModelProperty(value = "资源属性（json）")
    private String properties;
    @ApiModelProperty(value = "应用id")
    private String appId;
}

