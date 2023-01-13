package org.dows.account.dto;

import io.swagger.annotations.Api;
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
@ApiModel(value = "AccountOrgGroupDTO对象", description = "账号-组织架构(树形参数)")
public class AccountOrgGroupDTO {

    /**
     * 组织架构-实例
     */
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("组织架构父ID")
    private Integer pId;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("组织架构名称")
    private String orgName;

    @ApiModelProperty("组织架构code")
    private String orgCode;

    @ApiModelProperty("组织架构头像")
    private String profile;

    @ApiModelProperty("组织架构描述")
    private String orgDescr;

    @ApiModelProperty("组织架构类型")
    private Integer orgTyp;

    @ApiModelProperty("组织架构排序")
    private Integer orgSorted;

    @ApiModelProperty("组织架构状态")
    private Integer orgStatus;

    @ApiModelProperty("组织架构创建时间")
    private Date orgDt;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    /**
     * 组-实例
     */

    @ApiModelProperty("主键ID")
    private Long groupPrimaryId;

    @ApiModelProperty("负责人账号ID")
    private String accountId;

    @ApiModelProperty("负责人用户ID")
    private String userId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("负责人名称")
    private String owner;

    @ApiModelProperty("负责人电话")
    private String ownerPhone;

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String groupDescr;

    @ApiModelProperty("")
    private Date groupDt;

}
