package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountOrderStatisticsVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "总订单量")
    private Integer orderCountAll;

    @ApiModelProperty(value = "总消费金额")
    private Integer orderAmountAll;

    @ApiModelProperty(value = "消费次数")
    private Integer consumeNum;





















}
