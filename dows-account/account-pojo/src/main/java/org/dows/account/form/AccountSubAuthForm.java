package org.dows.account.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 子账号权限(AccountSubAuth)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountSub对象", description = "子账号权限")
public class AccountSubAuthForm implements Serializable {
    private static final long serialVersionUID = 471265538286310949L;

    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("全部营销权限:1关闭  2开启")
    private Integer authAll;

    @ApiModelProperty("收银台:1关闭  2开启")
    private Integer authCashier;

    @ApiModelProperty("菜退赠损，订单退款:1关闭  2开启")
    private Integer authOrder;

    @ApiModelProperty("报表:1关闭  2开启")
    private Integer authReport;

}

