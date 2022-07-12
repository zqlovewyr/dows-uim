package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-操作记录维度信息(AccountLogs)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:28
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountLogs对象", description = "账号-操作记录维度信息")
public class AccountLogsForm implements Serializable {
    private static final long serialVersionUID = 400949252428669933L;

    @ApiModelProperty(value = "用户id")
    private Long accountId;
    @ApiModelProperty(value = "操作类型(crud)")
    private Integer operatorType;
    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;
    @ApiModelProperty(value = "记录的值json")
    private String recordVal;
    @ApiModelProperty(value = "应用ID")
    private String tenantId;
}

