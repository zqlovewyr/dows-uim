package org.dows.account.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号-地址(实名认证后)(AccountAddress)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountAddress对象", description = "账号-地址(实名认证后)")
public class AccountAddress implements CrudEntity {
    private static final long serialVersionUID = 5485651946034818845L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

