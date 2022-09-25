package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-公司维度信息(UserCompany)Query类
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
public class UserCompanyQuery implements Serializable {
    private static final long serialVersionUID = -49726003000222809L;

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
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    private String companyCode;
    @ApiModelProperty(value = "公司类型")
    private Integer companyTyp;
    @ApiModelProperty(value = "公司营业执照编号")
    private String licenseNo;
    @ApiModelProperty(value = "法人")
    private String legalPerson;
    @ApiModelProperty(value = "注册资金")
    private Double regFund;
    @ApiModelProperty(value = "地址ID")
    private Long adressId;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "经营范围")
    private String bizScope;
    @ApiModelProperty(value = "商户号")
    private String merchantNo;
    @ApiModelProperty(value = "时间戳")
    private Date dt;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
}

