package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountConsumptionVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "客户分布类别")
    private Integer distributionType;


    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "数量Str")
    private String countStr;

    private List<AccountDistributionListVo> listVos;

    @Data
    class AccountDistributionListVo{

        @ApiModelProperty(value = "次数")
        private Integer count;
        @ApiModelProperty(value = "人数")
        private Integer preCount;
        @ApiModelProperty(value = "人数Str")
        private String preCountStr;
        @ApiModelProperty(value = "占比")
        private Integer rate;

    }
}
