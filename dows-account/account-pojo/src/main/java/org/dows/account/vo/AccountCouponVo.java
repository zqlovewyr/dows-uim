package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dows.account.util.MyConstant;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountCouponVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "优惠券名称")
    private String couponName;
    @ApiModelProperty(value = "优惠券金额")
    private BigDecimal couponAmount;
    @ApiModelProperty(value = "优惠券描述")
    private String couponRemake;
    @ApiModelProperty(value = "优惠券说明")
    private String couponExplain;

    @ApiModelProperty(value = "优惠券状态")
    private Integer couponType;

    @ApiModelProperty(value = "优惠券有效期")
    @JsonFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT)
    private Data couponEndTime;
























}
