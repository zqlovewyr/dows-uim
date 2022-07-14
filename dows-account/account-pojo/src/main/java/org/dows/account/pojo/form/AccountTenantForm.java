package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-租户维度信息(AccountTenant)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:52
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
    private static final long serialVersionUID = -12840102471239270L;

    @ApiModelProperty(value = "账号ID")
    private String accountId;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
}

