package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountConsumptionVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "消费能力分析类别")
    private Integer consumptionType;

    @ApiModelProperty(value = "次数")
    private Integer count;
    @ApiModelProperty(value = "人数")
    private Integer preCount;
    @ApiModelProperty(value = "人数Str")
    private String preCountStr;
    @ApiModelProperty(value = "占比")
    private Integer rate;
}
