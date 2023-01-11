package org.dows.account.biz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrgDTO对象", description = "账号-组织架构")
public class AccountOrgDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "id")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "父ID(父级组织ID)")
    private Long pid;

    @ApiModelProperty(value = "组织名称", required = true)
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织code")
    private String orgCode;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty(value = "应用ID", required = true)
    private String appId;

    @ApiModelProperty(value = "租户ID", required = true)
    private String tenantId;

    @ApiModelProperty(value = "角色Id rbac-role", example = "传值表示组织成员全部拥有对应角色权限")
    private Long rbacRoleId;

    @ApiModelProperty("组织成员集合")
    private List<AccountOrgGroupDTO> accountOrgGroups;

}
