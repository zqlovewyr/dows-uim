package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-地址维度(UserAddress)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:11
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAddressForm 表单对象", description = "用户-地址维度")
public class UserAddressForm implements Serializable {
    private static final long serialVersionUID = -43518328220119268L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("国家编号")
    private String countryNo;

    @ApiModelProperty("国家")
    private String countryName;

    @ApiModelProperty("国家简称")
    private String countryCode;

    @ApiModelProperty("省编号")
    private String provinceNo;

    @ApiModelProperty("省名称")
    private String provinceName;

    @ApiModelProperty("省简称")
    private String provinceCode;

    @ApiModelProperty("城市编号")
    private String cityNo;

    @ApiModelProperty("城市名")
    private String cityName;

    @ApiModelProperty("市简称")
    private String cityCode;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("街道编码")
    private String streetNo;

    @ApiModelProperty("街道名称")
    private String streetName;

    @ApiModelProperty("区县编码")
    private String districtNo;

    @ApiModelProperty("区县名称")
    private String districtName;

    @ApiModelProperty("邮编")
    private String zipCode;

    @ApiModelProperty("业务线")
    private String bizline;


}

