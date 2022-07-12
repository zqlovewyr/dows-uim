package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号-会员维度信息(AccountMembers)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:28
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountMembers对象", description = "账号-会员维度信息")
public class AccountMembers implements CrudEntity {
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("会员id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    @ApiModelProperty("会员级别ID")
    private Integer memberGradeId;
    @ApiModelProperty("会员级别名称")
    private String memberGradeName;
    @ApiModelProperty("会员卡卡号")
    private String cardNo;
    @ApiModelProperty("总佣金")
    private BigDecimal brokerage;
    @ApiModelProperty("推荐佣金")
    private BigDecimal referrerBrokerage;
    @ApiModelProperty("被推荐佣金")
    private BigDecimal proposedBrokerage;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("是否有效: 0 失效, 1 生效")
    private Integer state;
    @ApiModelProperty("租户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}
