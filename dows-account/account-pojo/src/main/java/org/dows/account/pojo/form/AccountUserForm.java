package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-用户维度信息(AccountUser)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:38
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountUser对象", description = "账号-用户维度信息")
public class AccountUserForm implements Serializable {
    private static final long serialVersionUID = 619047435161979243L;

    @ApiModelProperty(value = "账号id")
    private Long accountId;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

