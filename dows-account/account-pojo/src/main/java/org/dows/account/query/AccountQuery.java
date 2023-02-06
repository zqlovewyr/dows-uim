package org.dows.account.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.framework.crud.api.PageQuery;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountQuery extends PageQuery {
    private LocalDate createStartDate;

    private LocalDate createEndDate;

    private LocalDate birthdayStartDate;

    private LocalDate birthdayEndDate;

    @ApiModelProperty(value = "信息是否全")
    private Boolean infoIfFull;

    private Boolean moneySort;

    private Boolean activeSort;

    private String accountName;

    Boolean lastOrderTimeSort;
}
