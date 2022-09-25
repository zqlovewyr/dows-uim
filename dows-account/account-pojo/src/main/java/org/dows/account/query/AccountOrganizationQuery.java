package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-组织架构维度信息(AccountOrganization)Query类
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
public class AccountOrganizationQuery implements Serializable {
    private static final long serialVersionUID = -93393093780181467L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-权限ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "父ID")
    private Long orgPid;
    @ApiModelProperty(value = "组code")
    private String orgCode;
    @ApiModelProperty(value = "组织架构名")
    private String orgName;
    @ApiModelProperty(value = "组织类型(0:group组,1:element元素)")
    private Integer orgTyp;
    @ApiModelProperty(value = "组织架构ICON")
    private String orgIcon;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}

