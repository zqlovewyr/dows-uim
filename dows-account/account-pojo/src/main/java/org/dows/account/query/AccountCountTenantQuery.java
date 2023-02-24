package org.dows.account.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.api.PageQuery;

import java.util.Date;

@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountCountTenantQuery {

    @ApiModelProperty(value = "开始时间yyyy-MM-dd")
    private Date startDate;

    @ApiModelProperty(value = "时间yyyy-MM-dd")
    private Date endDate;

    @ApiModelProperty(value = "门店ID")
    private String storeId;

    @ApiModelProperty(value = "门店名称")
    private String storeName;
    @ApiModelProperty(value = "区域")
    private String district;
    @ApiModelProperty(value = "品牌")
    private Integer storeBrand;

    @ApiModelProperty(value = "门店类型")
    private Integer storeType;

    @ApiModelProperty(value = "门店模式")
    private Integer storePattern;
    @ApiModelProperty(value = "订单类型")
    private Integer orderType;
}
