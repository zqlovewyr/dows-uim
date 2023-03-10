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
public class AccountGroupSearchVO implements Serializable {
    private static final long serialVersionUID = -7477493379462499452L;
    @ApiModelProperty("组织架构ID<集合>")
    private Set<String> orgIds;
    @ApiModelProperty("组织架构名")
    private String orgName;
    @ApiModelProperty("账号ID<集合>")
    private Set<String> accountIds;
    @ApiModelProperty("账号名")
    private String accountName;
    @ApiModelProperty("应用ID<集合>")
    private Set<String> appIds;
}
