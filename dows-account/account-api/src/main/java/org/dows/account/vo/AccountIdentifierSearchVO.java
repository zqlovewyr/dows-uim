package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AccountIdentifierSearchVO {
    @ApiModelProperty("账号ID")
    private Set<String> accountIds;
    @ApiModelProperty("应用ID")
    private String appId;
}
