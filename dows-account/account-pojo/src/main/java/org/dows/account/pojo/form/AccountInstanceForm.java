package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-实例维度信息(AccountInstance)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:29
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
    private static final long serialVersionUID = -11799921866059343L;

    @ApiModelProperty(value = "分布式ID")
    private String accountId;
    @ApiModelProperty(value = "账号名")
    private String accountName;
    @ApiModelProperty(value = "账号密码")
    private String accountPwd;
    @ApiModelProperty(value = "账号类型")
    private Integer accountType;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "应用 id")
    private String tenantId;
    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;
    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;
}

