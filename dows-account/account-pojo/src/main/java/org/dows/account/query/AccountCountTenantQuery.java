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
}
