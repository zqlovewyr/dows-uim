package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-账号组维度信息(AccountGroup)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:20
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountGroup对象", description = "账号-账号组维度信息")
public class AccountGroupForm implements Serializable {
    private static final long serialVersionUID = 333936444784677628L;

    @ApiModelProperty(value = "分布式ID")
    private String groupId;
    @ApiModelProperty(value = "组织架构ID")
    private String orgId;
    @ApiModelProperty(value = "岗位名")
    private String orgName;
    @ApiModelProperty(value = "岗位code")
    private String orgCode;
    @ApiModelProperty(value = "icon")
    private String orgIcon;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "账号ID")
    private String accountId;
    @ApiModelProperty(value = "账号名")
    private String accountName;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "用户真实姓名")
    private String userName;
    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    @ApiModelProperty(value = "用户电话")
    private String userPhone;
    @ApiModelProperty(value = "组编号|工号")
    private Integer groupNo;
    @ApiModelProperty(value = "描述")
    private String descr;
}

