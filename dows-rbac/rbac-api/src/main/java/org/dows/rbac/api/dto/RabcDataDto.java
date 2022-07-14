package org.dows.rbac.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: RBAC-数据资源(RabcData)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:03
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RabcData对象", description = "RBAC-数据资源")
public class RabcDataDto implements Serializable {
    private static final long serialVersionUID = -46490913871851647L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键ID(数据库自增)")
    private Long id;
    @ApiModelProperty(value = "资源ID(分布式id)")
    private String resourceId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
