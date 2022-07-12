package org.dows.account.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-会员维度信息(AccountMembers)Query类
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
public class AccountMembersQuery implements Serializable {
    private static final long serialVersionUID = -80162816028101329L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "会员id")
    private Long accountId;
    @ApiModelProperty(value = "会员级别ID")
    private Integer memberGradeId;
    @ApiModelProperty(value = "会员级别名称")
    private String memberGradeName;
    @ApiModelProperty(value = "会员卡卡号")
    private String cardNo;
    @ApiModelProperty(value = "总佣金")
    private Double brokerage;
    @ApiModelProperty(value = "推荐佣金")
    private Double referrerBrokerage;
    @ApiModelProperty(value = "被推荐佣金")
    private Double proposedBrokerage;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
    @ApiModelProperty(value = "是否有效: 0 失效, 1 生效")
    private Integer state;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}

