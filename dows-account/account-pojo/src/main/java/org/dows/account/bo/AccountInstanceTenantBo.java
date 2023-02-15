package org.dows.account.bo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-实例(AccountInstance)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@SuppressWarnings("serial")
@Data
public class AccountInstanceTenantBo implements Serializable {
    private static final long serialVersionUID = 752274823268208486L;

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

    @ApiModelProperty(value = "门店ID")
    private String storeId;

    @ApiModelProperty("客户分布类型")
    private Integer distributionType;

    @ApiModelProperty("消费能力分析类型")
    private Integer consumptionType;

    @ApiModelProperty("客户分布类型 设置天数客户分析后端使用，前端无需传参")
    private Integer distributionCount;

    @ApiModelProperty("消费频次分析，客户分析后端使用，前端无需传参")
    private Integer frequencyType;

    @ApiModelProperty("设置消费能力金额开始，前端无需传参")
    private Integer frequencyStartCount;

    @ApiModelProperty("设置消费能力金额开始，前端无需传参")
    private Integer frequencyEndCount;

}

