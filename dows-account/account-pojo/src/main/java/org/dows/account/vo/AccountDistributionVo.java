package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDistributionVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "客户分布类别")
    private Integer distributionType;


    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "数量名称")
    private String countStr;

    @ApiModelProperty(value = "占比")
    private Integer rate;
    @ApiModelProperty(value = "人均消费")
    private BigDecimal amount;
}
