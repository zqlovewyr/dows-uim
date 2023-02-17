package org.dows.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/16 17:09
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAddressVo对象", description = "用户-住址信息")
public class UserAddressVo {
    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

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

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;
}
