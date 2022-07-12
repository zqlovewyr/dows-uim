package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-标签维度信息(AccountTags)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:35
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTags对象", description = "账号-标签维度信息")
public class AccountTagsForm implements Serializable {
    private static final long serialVersionUID = 525669062457140901L;

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

