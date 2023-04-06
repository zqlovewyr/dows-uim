package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 用户储蓄卡积分优惠券VO类
 * @since 2023-04-05
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAssetPointsAndCouponVo对象", description = "用户-储蓄卡积分优惠券信息")
public class UserAssetPointsAndCouponVo  {

    @ApiModelProperty(value = "储蓄卡余额")
    private BigDecimal cardMoney = new BigDecimal("0.00");

    @ApiModelProperty(value = "积分")
    private int points;

    @ApiModelProperty(value = "优惠券数量")
    private int couponCount;
}

