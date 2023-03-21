package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账号-地址(实名认证后)(AccountAddress)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountAddressForm 表单对象", description = "账号-地址(实名认证后)")
public class AccountAddressForm implements Serializable {
    private static final long serialVersionUID = 780642613565779406L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    /** 是否默认收货地址 Y:是 ， N:否 */
    @ApiModelProperty(name = "是否默认收货地址 Y:是 ， N:否")
    private String ifDefaultAddress;

    /** 联系人姓名 */
    @ApiModelProperty(name = "联系人姓名")
    private String contactName;

    /** 联系人电话 */
    @ApiModelProperty(name = "联系人电话")
    private String contactPhone;

    /** 省份 */
    @ApiModelProperty(name = "省份")
    private Integer province;

    /** 市 */
    @ApiModelProperty(name = "市")
    private Integer city;

    /** 区、县 */
    @ApiModelProperty(name = "区、县")
    private Integer area;

    /** 街道 */
    @ApiModelProperty(name = "街道")
    private Integer street;

    /** 详细地址 */
    @ApiModelProperty(name = "详细地址")
    private String fullAddress;

    /**
     * 经度
     */
    @ApiModelProperty(name = "经度")
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @ApiModelProperty(name = "纬度")
    private BigDecimal latitude;

    @JsonIgnore
    private Boolean deleted;


}

