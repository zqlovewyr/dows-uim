package org.dows.account.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-地址维度信息(UserAdress)Query类
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
public class UserAdressQuery implements Serializable {
    private static final long serialVersionUID = 170290496581097736L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

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

