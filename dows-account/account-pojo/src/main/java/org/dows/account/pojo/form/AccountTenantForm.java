package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-租户维度信息(AccountTenant)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:36
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTenant对象", description = "账号-租户维度信息")
public class AccountTenantForm implements Serializable {
    private static final long serialVersionUID = -34394977646855476L;

    @ApiModelProperty(value = "账号ID")
    private Long accountId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "商户号")
    private String merchantNo;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
}

