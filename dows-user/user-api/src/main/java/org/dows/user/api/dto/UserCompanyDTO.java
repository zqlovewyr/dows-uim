package org.dows.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/8 13:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserCompanyDTO对象", description = "用户-公司信息")
public class UserCompanyDTO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("地址ID")
    private String adressId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("")
    private String companyCode;

    @ApiModelProperty("社会统一信用代码")
    private String certNo;

    @ApiModelProperty("公司营业执照编号")
    private String licenseNo;

    @ApiModelProperty("法人")
    private String legalPerson;

    @ApiModelProperty("经营范围")
    private String bizScope;

    @ApiModelProperty("营业执照照片")
    private String licensePic;

    @ApiModelProperty("商户号")
    private String merchantNo;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("注册资金")
    private BigDecimal regFund;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("时间戳")
    private Date dt;
}
