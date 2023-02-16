package org.dows.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 * @date 2023/1/30 16:56
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserProfileVo对象", description = "用户-档案信息")
public class UserProfileVo {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("本人电话")
    private String phone;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("教育程度")
    private String degree;

    @ApiModelProperty("婚姻与否")
    private String married;

    @ApiModelProperty("工作单位")
    private String companyName;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("联系人电话")
    private String contactNum;

    @ApiModelProperty("省份名")
    private String provinceName;

    @ApiModelProperty("城市名")
    private String cityName;

    @ApiModelProperty("区县名称")
    private String districtName;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("家庭电话")
    private String telephone;

    @ApiModelProperty("邮政编码")
    private String zipCode;

    @ApiModelProperty("居住总面积")
    private String acreage;

    @ApiModelProperty("人均居住面积")
    private String perarea;

    @ApiModelProperty("房屋类型")
    private String houseType;

    @ApiModelProperty("燃料")
    private String fuel;

    @ApiModelProperty("厨房排风设施")
    private String exhaustFacility;

    @ApiModelProperty("厨房使用方式")
    private String kitchen;

    @ApiModelProperty("饮水")
    private String water;

    @ApiModelProperty("畜牧栏种类")
    private String rearPoultry;

    @ApiModelProperty("医疗费用支付方式")
    private String medicalPay;

    @ApiModelProperty("地区级联id")
    private String cascadeAddressId;
}
