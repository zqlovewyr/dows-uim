package org.dows.account.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-标签维度信息(AccountTag)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:45
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTag对象", description = "账号-标签维度信息")
public class AccountTagForm implements Serializable {
    private static final long serialVersionUID = 184280444688527117L;

    @ApiModelProperty(value = "用户id")
    private Long accountId;
    @ApiModelProperty(value = "标签类目")
    private String tagCategory;
    @ApiModelProperty(value = "标签名")
    private String tagName;
    @ApiModelProperty(value = "标签色")
    private String tagColor;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

