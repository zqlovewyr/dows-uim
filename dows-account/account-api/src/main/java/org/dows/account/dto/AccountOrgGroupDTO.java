package org.dows.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrgGroupDTO对象", description = "账号-组织架构(树形参数)")
public class AccountOrgGroupDTO {

    /**
     * 组织架构-实例
     */
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("组织架构父ID")
    private Integer pId;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织架构名称")
    private String orgName;

    @ApiModelProperty("组织机构电话")
    private String phone;

    @ApiModelProperty("组织架构code")
    private String orgCode;

    @ApiModelProperty("组织架构头像")
    private String profile;

    @ApiModelProperty("组织架构描述")
    private String orgDescr;

    @ApiModelProperty("组织架构类型")
    private Integer orgType;

    @ApiModelProperty("组织架构排序")
    private Integer sorted;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "有效开始时间")
    private Date indate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "有效结束时间")
    private Date expdate;

    /**
     * 组-实例
     */

    @ApiModelProperty("组名称")
    private String groupInfoName;

    @ApiModelProperty("负责人账号ID")
    private String accountId;

    @ApiModelProperty("负责人用户ID")
    private String userId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("负责人名称")
    private String owner;

    @ApiModelProperty("负责人电话")
    private String ownerPhone;

    @ApiModelProperty("负责人职位")
    private String ownerPosition;

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String groupDescr;

    @ApiModelProperty("")
    private Date dt;

    @ApiModelProperty("邮编")
    private String postal;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("机构网址")
    private String website;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("身份唯一识别标识")
    private String identifier;
}
