package org.dows.account.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 门店-实例(StoreInstance)实体类
 *
 * @author lait.zhang
 * @since 2022-11-11 00:23:41
 */
@SuppressWarnings("serial")
@Data

public class StoreResVo {

    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("业态ID")
    private String ecoId;

    @ApiModelProperty("业态")
    private String ecoBiz;

    @ApiModelProperty("父ID(pid空时为总店)")
    private String storePid;

    @ApiModelProperty("门店ID标识")
    private String storeId;

    @ApiModelProperty("名称 ")
    private String name;

    @ApiModelProperty("门面照")
    private String profile;

    @ApiModelProperty("国家 ")
    private String country;

    @ApiModelProperty("区域")
    private String district;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("商户号")
    private String merchantNo;

    @ApiModelProperty("租户号")
    private String tenantId;

    @ApiModelProperty("b门店编号 6位 199 999- 99999")
    private String storeNo;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("门店类型 1正餐 2快餐")
    private Integer storeType;

    @ApiModelProperty("门店类型 1正餐 2快餐")
    private String storeTypeShow;

    @ApiModelProperty("门店模式 1直营 2加盟")
    private Integer storePattern;

    @ApiModelProperty("门店模式 1直营 2加盟")
    private String storePatternShow;

    @ApiModelProperty("品牌")
    private Integer storeBrand;

    @ApiModelProperty("所属品牌")
    private String storeBrandShow;

    @ApiModelProperty("开业日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date openDate;

    @ApiModelProperty("状态 1休息 2营业中")
    private Integer state;

    @ApiModelProperty("营业状态 1休息 2营业中")
    private String stateShow;

    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;

    @ApiModelProperty(value = "账号名")
    private String accountName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty("分成比例")
    private Double commissionRatio;

    @ApiModelProperty("联系人 ")
    private String contacts;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

}

