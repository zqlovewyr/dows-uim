package org.dows.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrgGroup对象", description = "账号-组织架构(树形参数)")
public class AccountOrgGroupDTO {

    @ApiModelProperty("组织ID")
    private String orgId;

    @ApiModelProperty("组织名")
    private String orgName;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("")
    private Date dt;

}
