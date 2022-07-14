package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-主体角色维度信息(AccountRole)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:42
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRole对象", description = "账号-主体角色维度信息")
public class AccountRoleDto implements Serializable {
    private static final long serialVersionUID = 774367013675263127L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "角色码")
    private String roleCode;
    @ApiModelProperty(value = "主体ID(账号ID/组ID)")
    private String principalId;
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
