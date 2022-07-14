package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-组织架构维度信息(AccountOrganization)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrganization对象", description = "账号-组织架构维度信息")
public class AccountOrganizationForm implements Serializable {
    private static final long serialVersionUID = 354566431157876793L;

    @ApiModelProperty(value = "分布式ID")
    private String orgId;
    @ApiModelProperty(value = "父ID")
    private Long orgPid;
    @ApiModelProperty(value = "组织架构名")
    private String orgName;
    @ApiModelProperty(value = "组code")
    private String orgCode;
    @ApiModelProperty(value = "名称首字母")
    private String nameLetter;
    @ApiModelProperty(value = "组织类型(0:group组,1:element元素)")
    private Integer orgType;
    @ApiModelProperty(value = "组织架构ICON")
    private String orgIcon;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "关联的主体公司")
    private String companyId;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "描述")
    private String descr;
}

