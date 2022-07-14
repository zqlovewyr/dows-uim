package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-用户维度信息(AccountUser)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:56
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
    private static final long serialVersionUID = 107772338009179556L;

    @ApiModelProperty(value = "账号id")
    private String accountId;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

