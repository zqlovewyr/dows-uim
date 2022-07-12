package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-地址维度信息(UserAdress)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:54
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAdress对象", description = "用户-地址维度信息")
public class UserAdressDto implements Serializable {
    private static final long serialVersionUID = -88575482291019955L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 国家编号
     */
    @ApiModelProperty(value = "国家编号")
    private String countryNo;
    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String countryName;
    /**
     * 国家简称
     */
    @ApiModelProperty(value = "国家简称")
    private String countryCode;
    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    private String provinceNo;
    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    /**
     * 省简称
     */
    @ApiModelProperty(value = "省简称")
    private String provinceCode;
    /**
     * 城市编号
     */
    @ApiModelProperty(value = "城市编号")
    private String cityNo;
    /**
     * 城市名
     */
    @ApiModelProperty(value = "城市名")
    private String cityName;
    /**
     * 市简称
     */
    @ApiModelProperty(value = "市简称")
    private String cityCode;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;
    /**
     * 街道编码
     */
    @ApiModelProperty(value = "街道编码")
    private String streetNo;
    /**
     * 街道名称
     */
    @ApiModelProperty(value = "街道名称")
    private String streetName;
    /**
     * 区县编码
     */
    @ApiModelProperty(value = "区县编码")
    private String districtNo;
    /**
     * 区县名称
     */
    @ApiModelProperty(value = "区县名称")
    private String districtName;
    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String zipCode;
    /**
     * 地址类型
     */
    @ApiModelProperty(value = "地址类型")
    private Integer typ;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
