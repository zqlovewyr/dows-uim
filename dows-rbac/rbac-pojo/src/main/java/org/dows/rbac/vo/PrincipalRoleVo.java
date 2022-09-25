package org.dows.rbac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-主体角色维度信息(PrincipalRole)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:40
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PrincipalRole对象", description = "账号-主体角色维度信息")
public class PrincipalRoleVo implements Serializable {
    private static final long serialVersionUID = 468470985541642207L;

    @ApiModelProperty(value = "id")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

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

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

}

