package org.dows.account.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.api.PageQuery;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountQuery extends PageQuery {

    @ApiModelProperty(value = "注册开始时间yyyy-MM-dd")
    private Date createStartDate;

    @ApiModelProperty(value = "注册截止时间yyyy-MM-dd")
    private Date createEndDate;


    @ApiModelProperty(value = "生日开始时间yyyy-MM-dd")
    private Date birthdayStartDate;

    @ApiModelProperty(value = "生日截止时间yyyy-MM-dd")
    private Date birthdayEndDate;

    @ApiModelProperty(value = "是否筛选信息全的客户")
    private Boolean infoIfFull;

    @ApiModelProperty(value = "消费金额排序true升序false降序")
    private Boolean moneySort;

    @ApiModelProperty(value = "会员活跃度true升序false降序")
    private Boolean activeSort;

    @ApiModelProperty(value = "会员名称")
    private String accountName;

    @ApiModelProperty(value = "最后下单时间排序true升序false降序")
    Boolean lastOrderTimeSort;
}
