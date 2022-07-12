package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-公司维度信息(UserCompany)DTO类
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
@ApiModel(value = "UserCompany对象", description = "用户-公司维度信息")
public class UserCompanyDto implements Serializable {
    private static final long serialVersionUID = -49097480921170810L;

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
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    private String companyCode;
    /**
     * 公司类型
     */
    @ApiModelProperty(value = "公司类型")
    private Integer companyTyp;
    /**
     * 公司营业执照编号
     */
    @ApiModelProperty(value = "公司营业执照编号")
    private String licenseNo;
    /**
     * 法人
     */
    @ApiModelProperty(value = "法人")
    private String legalPerson;
    /**
     * 注册资金
     */
    @ApiModelProperty(value = "注册资金")
    private Double regFund;
    /**
     * 地址ID
     */
    @ApiModelProperty(value = "地址ID")
    private Long adressId;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String state;
    /**
     * 经营范围
     */
    @ApiModelProperty(value = "经营范围")
    private String bizScope;
    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String merchantNo;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;


}
