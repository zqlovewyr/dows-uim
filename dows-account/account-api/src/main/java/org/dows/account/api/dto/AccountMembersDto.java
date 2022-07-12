package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-会员维度信息(AccountMembers)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountMembers对象", description = "账号-会员维度信息")
public class AccountMembersDto implements Serializable {
    private static final long serialVersionUID = -13983020247269590L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 会员id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "会员id")
    private Long accountId;
    /**
     * 会员级别ID
     */
    @ApiModelProperty(value = "会员级别ID")
    private Integer memberGradeId;
    /**
     * 会员级别名称
     */
    @ApiModelProperty(value = "会员级别名称")
    private String memberGradeName;
    /**
     * 会员卡卡号
     */
    @ApiModelProperty(value = "会员卡卡号")
    private String cardNo;
    /**
     * 总佣金
     */
    @ApiModelProperty(value = "总佣金")
    private Double brokerage;
    /**
     * 推荐佣金
     */
    @ApiModelProperty(value = "推荐佣金")
    private Double referrerBrokerage;
    /**
     * 被推荐佣金
     */
    @ApiModelProperty(value = "被推荐佣金")
    private Double proposedBrokerage;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    /**
     * 是否有效: 0 失效, 1 生效
     */
    @ApiModelProperty(value = "是否有效: 0 失效, 1 生效")
    private Integer state;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
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
