package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 账号-会员维度信息(AccountMembers)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:29
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountMembers对象", description = "账号-会员维度信息")
public class AccountMembersForm implements Serializable {
    private static final long serialVersionUID = 588070093979444230L;

    @ApiModelProperty(value = "会员id")
    private Long accountId;
    @ApiModelProperty(value = "会员级别ID")
    private Integer memberGradeId;
    @ApiModelProperty(value = "会员级别名称")
    private String memberGradeName;
    @ApiModelProperty(value = "会员卡卡号")
    private String cardNo;
    @ApiModelProperty(value = "总佣金")
    private BigDecimal brokerage;
    @ApiModelProperty(value = "推荐佣金")
    private BigDecimal referrerBrokerage;
    @ApiModelProperty(value = "被推荐佣金")
    private BigDecimal proposedBrokerage;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "是否有效: 0 失效, 1 生效")
    private Integer state;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
}

