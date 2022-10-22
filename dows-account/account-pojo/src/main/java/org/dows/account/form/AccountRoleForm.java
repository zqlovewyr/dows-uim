package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-角色(AccountRole)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRoleForm 表单对象", description = "账号-角色")
public class AccountRoleForm implements Serializable {
    private static final long serialVersionUID = -76779896293547364L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色码")
    private String roleCode;

    @ApiModelProperty("主体ID")
    private String principalId;

    @ApiModelProperty("主体名称")
    private String principalName;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @JsonIgnore
    private Boolean deleted;


}

