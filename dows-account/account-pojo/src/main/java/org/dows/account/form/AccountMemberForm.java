package org.dows.account.form;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-会员(AccountMember)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:27
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountMemberForm 表单对象", description = "账号-会员")
public class AccountMemberForm implements Serializable {
    private static final long serialVersionUID = 394311261442249776L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号id")
    private String accountId;

    @ApiModelProperty("会员级别名称")
    private String gradeName;

    @ApiModelProperty("会员卡卡号")
    private String cardNo;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("总佣金")
    private BigDecimal brokerage;

    @ApiModelProperty("推荐佣金")
    private BigDecimal referrerBrokerage;

    @ApiModelProperty("被推荐佣金")
    private BigDecimal proposedBrokerage;

    @JsonIgnore
    private Boolean deleted;


}

