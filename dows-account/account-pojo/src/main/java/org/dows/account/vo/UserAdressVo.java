package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-地址维度信息(UserAdress)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:41
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAdress对象", description = "用户-地址维度信息")
public class UserAdressVo implements Serializable {
    private static final long serialVersionUID = 156772244010354654L;

    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "国家编号")
    private String countryNo;

    @ApiModelProperty(value = "国家")
    private String countryName;

    @ApiModelProperty(value = "国家简称")
    private String countryCode;

    @ApiModelProperty(value = "省编号")
    private String provinceNo;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "省简称")
    private String provinceCode;

    @ApiModelProperty(value = "城市编号")
    private String cityNo;

    @ApiModelProperty(value = "城市名")
    private String cityName;

    @ApiModelProperty(value = "市简称")
    private String cityCode;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "街道编码")
    private String streetNo;

    @ApiModelProperty(value = "街道名称")
    private String streetName;

    @ApiModelProperty(value = "区县编码")
    private String districtNo;

    @ApiModelProperty(value = "区县名称")
    private String districtName;

    @ApiModelProperty(value = "邮编")
    private String zipCode;

    @ApiModelProperty(value = "地址类型")
    private Integer typ;

    @ApiModelProperty(value = "时间戳")
    private Date dt;

}

