package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-组织架构维度信息(AccountOrganization)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:47
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrganization对象", description = "账号-组织架构维度信息")
public class AccountOrganizationDto implements Serializable {
    private static final long serialVersionUID = -75429587271706338L;

    /**
     * 账号-权限ID
     */
    @ApiModelProperty(value = "账号-权限ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 父ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "父ID")
    private Long orgPid;
    /**
     * 组code
     */
    @ApiModelProperty(value = "组code")
    private String orgCode;
    /**
     * 组织架构名
     */
    @ApiModelProperty(value = "组织架构名")
    private String orgName;
    /**
     * 组织类型(0:group组,1:element元素)
     */
    @ApiModelProperty(value = "组织类型(0:group组,1:element元素)")
    private Integer orgTyp;
    /**
     * 组织架构ICON
     */
    @ApiModelProperty(value = "组织架构ICON")
    private String orgIcon;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 乐观锁, 默认: 0
     */
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    /**
     * 时间戳/创建时间
     */
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    /**
     * 是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
     */
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
