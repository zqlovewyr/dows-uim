package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 账号-账号组维度信息(AccountGroup)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:23
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
    private static final long serialVersionUID = -42932102966026321L;

    @ApiModelProperty(value = "组织架构ID")
    private Long orgId;
    @ApiModelProperty(value = "账号ID")
    private Long accountId;
    @ApiModelProperty(value = "岗位名")
    private String orgName;
    @ApiModelProperty(value = "岗位code")
    private String orgCode;
    @ApiModelProperty(value = "icon")
    private String orgIcon;
    @ApiModelProperty(value = "描述")
    private BigDecimal descr;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

