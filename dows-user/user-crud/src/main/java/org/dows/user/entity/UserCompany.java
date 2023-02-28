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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户-公司(UserCompany)实体类
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
@ApiModel(value = "UserCompany对象", description = "用户-公司")
public class UserCompany implements CrudEntity {
    private static final long serialVersionUID = 126896658946407053L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("用户ID")
    private Long userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;


}

