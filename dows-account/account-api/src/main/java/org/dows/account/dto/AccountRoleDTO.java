package org.dows.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/31 9:27
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRoleDTO对象", description = "账号-角色实例信息")
public class AccountRoleDTO {

    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @ApiModelProperty("角色码")
    private String roleCode;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("主体类型")
    /* runsix:0-个人，1-组 */
    private Integer principalType;

    @ApiModelProperty("主体ID")
    private String principalId;

    @ApiModelProperty("主体名称")
    private String principalName;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
