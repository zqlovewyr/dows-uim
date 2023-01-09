package org.dows.user.entity;

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

import java.util.Date;

/**
 * 用户-地址维度(UserAddress)实体类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "UserAddress对象", description = "用户-地址维度")
public class UserAddress implements CrudEntity {
    private static final long serialVersionUID = -37309210172185726L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键")
    private Long id;

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

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

