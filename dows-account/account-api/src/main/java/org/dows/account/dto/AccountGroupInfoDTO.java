package org.dows.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/9 17:44
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroupInfoDTO对象", description = "账号-组负责人信息")
public class AccountGroupInfoDTO {

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("组别ID")
    private String groupInfoId;

    @ApiModelProperty("组名/团队名称")
    private String groupInfoName;

    @ApiModelProperty("组别状态(0-禁用,1-启用)")
    private Integer status;

    @ApiModelProperty("邮编")
    private String postal;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("机构网址")
    private String website;

    @ApiModelProperty("负责人账户ID")
    private String accountId;

    @ApiModelProperty("负责人用户ID")
    private String userId;

    @ApiModelProperty("负责人名称")
    private String owner;

    @ApiModelProperty("负责人职位")
    private String ownerPosition;

    @ApiModelProperty("负责人电话")
    private String ownerPhone;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("组织架构名称")
    private String orgName;

    @ApiModelProperty("组织架构Code")
    private String orgCode;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String descr;

    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private Date dt;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "有效开始时间")
    private Date indate;

    @ApiModelProperty(value = "有效结束时间")
    private Date expdate;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
