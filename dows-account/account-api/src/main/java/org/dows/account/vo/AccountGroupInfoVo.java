package org.dows.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/9 20:25
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountGroupInfoVo对象", description = "账号-组信息")
public class AccountGroupInfoVo {

    @ApiModelProperty("自增主键ID")
    private String id;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("组织架构名称")
    private String orgName;

    @ApiModelProperty("组织架构Code")
    private String orgCode;

    @ApiModelProperty("组织架构当前人数")
    private Integer num;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("邮编")
    private String postal;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("机构网址")
    private String website;

    @ApiModelProperty("组别ID")
    private String groupInfoId;

    @ApiModelProperty("团队描述")
    private String groupDescr;

    @ApiModelProperty("组名/团队名称")
    private String groupInfoName;

    @ApiModelProperty("组别状态")
    private Integer status;

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

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String descr;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("组织类型")
    private Integer orgType;

    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private Date dt;

    @ApiModelProperty("省级行政区-地级行政区")
    private String absoluteArea;

    @ApiModelProperty(value = "有效时间(开始时间)")
    private Date indate;

    @ApiModelProperty(value = "有效时间(失效时间)")
    private Date expdate;
}
