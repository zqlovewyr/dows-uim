package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 账号-组织架构维度信息(AccountOrganization)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountOrg对象", description = "账号-组织架构维度信息")
public class AccountOrgVo implements Serializable {
    private static final long serialVersionUID = -99474169347524042L;

    @ApiModelProperty(value = "账号-组ID")
    private String id;

    @ApiModelProperty("实体域组织机构ID")
    private String orgId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID(pid空时为总店)")
    private Long pid;

    @ApiModelProperty(value = "联系电话")
    private String telePhone;

    @ApiModelProperty(value = "组code")
    private String orgCode;

    @ApiModelProperty(value = "组织架构名")
    private String orgName;

    @ApiModelProperty(value = "机构人数")
    private Integer currentNum;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty(value = "头像")
    private String profile;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("组织描述")
    private String descr;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty(value = "组织下-账号")
    private List<AccountGroupVo> accountGroupList;

}

