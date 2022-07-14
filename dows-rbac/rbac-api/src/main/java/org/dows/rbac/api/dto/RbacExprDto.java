package org.dows.rbac.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: rbac-权限表达式(RbacExpr)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:14
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacExpr对象", description = "rbac-权限表达式")
public class RbacExprDto implements Serializable {
    private static final long serialVersionUID = -15934114576939718L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "自增主键")
    private Long id;
    @ApiModelProperty(value = "分布式主键")
    private String exprId;
    @ApiModelProperty(value = "键")
    private String key;
    @ApiModelProperty(value = "条件(> = < in等)")
    private String criteria;
    @ApiModelProperty(value = "值")
    private String val;
    @ApiModelProperty(value = "逻辑（and or）")
    private String logic;
    @ApiModelProperty(value = "应用id")
    private String appId;
    @ApiModelProperty(value = "租户号")
    private String tenantId;
    private String descr;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
