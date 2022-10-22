package org.dows.account.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.math.BigDecimal;

/**
 * 账号-会员(AccountMember)实体类
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountMember对象", description = "账号-会员")
public class AccountMember implements CrudEntity {
    private static final long serialVersionUID = -69199158221306031L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键")
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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}

