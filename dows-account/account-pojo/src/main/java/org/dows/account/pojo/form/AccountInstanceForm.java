package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-实例维度信息(AccountInstance)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:26
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstance对象", description = "账号-实例维度信息")
public class AccountInstanceForm implements Serializable {
    private static final long serialVersionUID = -36008026925702739L;

    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;
    @ApiModelProperty(value = "账号名")
    private String accountName;
    @ApiModelProperty(value = "账号密码")
    private String accountPwd;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "应用 id")
    private String tenantId;
    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;
    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;
}

