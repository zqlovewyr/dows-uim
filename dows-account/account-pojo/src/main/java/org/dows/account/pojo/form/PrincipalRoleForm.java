package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-主体角色维度信息(PrincipalRole)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:45:33
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PrincipalRole对象", description = "账号-主体角色维度信息")
public class PrincipalRoleForm implements Serializable {
    private static final long serialVersionUID = 619380602619740637L;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色码")
    private String roleCode;
    @ApiModelProperty(value = "主体ID(账号/角色)")
    private Long principalId;
    @ApiModelProperty(value = "主体名称")
    private String principalName;
    @ApiModelProperty(value = "主体类型(0：账号，1：组)")
    private Integer principalTyp;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

