package org.dows.account.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountCouponBo {

    @ApiModelProperty(value = "账户ID")
    private Integer accountId;

    @ApiModelProperty(value = "优惠券类型 空为全部 1、已使用2、未使用3、已过期")
    private Integer couponType;


    @ApiModelProperty(value = "页码,默认为1")
    private Integer current = 1;
    @ApiModelProperty(value = "页大小,默认为10")
    private Integer size = 10;

    public void setCurrent(Integer current) {
        if (current == null || current <= 0){
            this.current = 1;
        }else{
            this.current = current;
        }
    }

    public void setSize(Integer size) {
        if (size == null || size <= 0){
            this.size = 10;
        }else{
            this.size = size;
        }
    }

}
