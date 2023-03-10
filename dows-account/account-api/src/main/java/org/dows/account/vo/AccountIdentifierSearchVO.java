package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountIdentifierSearchVO implements Serializable {
    private static final long serialVersionUID = 7932163331771864259L;
    @ApiModelProperty("账号ID")
    private Set<String> accountIds;
    @ApiModelProperty("应用ID")
    private String appId;
}
