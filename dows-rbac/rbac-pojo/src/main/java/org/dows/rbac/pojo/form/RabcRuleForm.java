package org.dows.rbac.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: RBAC-权限规则(RabcRule)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 22:57:07
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RabcRule对象", description = "RBAC-权限规则")
public class RabcRuleForm implements Serializable {
    private static final long serialVersionUID = 688324239857210886L;

    @ApiModelProperty(value = "规则分布式id")
    private String ruleId;
    @ApiModelProperty(value = "权限规则名称")
    private String ruleName;
    @ApiModelProperty(value = "权限规则code")
    private String ruleCode;
    @ApiModelProperty(value = "资源ID(分布式id)")
    private String resourceId;
    @ApiModelProperty(value = "资源类型")
    private Integer resourceTyp;
    @ApiModelProperty(value = "规则对应的表达式(json)")
    private String expr;
    @ApiModelProperty(value = "应用id")
    private String appId;
    @ApiModelProperty(value = "租户id")
    private String tenantId;
}

