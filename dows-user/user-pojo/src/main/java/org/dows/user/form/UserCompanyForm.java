package org.dows.user.form;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-公司(UserCompany)表单
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
@ApiModel(value = "UserCompanyForm 表单对象", description = "用户-公司")
public class UserCompanyForm implements Serializable {
    private static final long serialVersionUID = 603712486803296524L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("地址ID")
    private Long adressId;

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

    @JsonIgnore
    private Boolean deleted;


}

