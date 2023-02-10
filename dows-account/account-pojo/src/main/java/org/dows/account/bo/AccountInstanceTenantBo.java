package org.dows.account.bo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

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

    @ApiModelProperty("客户分布类型")
    private Integer distributionType;

    @ApiModelProperty("消费能力分析类型")
    private Integer consumptionType;

}

