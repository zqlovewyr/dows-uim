package org.dows.account.form;


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
@ApiModel(value = "AccountInstanceBo 表单对象", description = "账号-实例")
public class AccountInstanceTenantForm implements Serializable {
    private static final long serialVersionUID = 752274823268208488L;

    @ApiModelProperty("开始日期")
    private Date startDate;

    @ApiModelProperty("结束日期")
    private Date endDate;

    @ApiModelProperty("客户分布类型1、性别2、年度3、生肖4、星座")
    private Integer distributionType;

    @ApiModelProperty("消费能力分析类型1、消费频次2、消费能力3、客户流失")
    private Integer consumptionType;

}

