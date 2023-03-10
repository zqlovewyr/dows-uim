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
public class AccountGroupInfoSearchVO implements Serializable {
    private static final long serialVersionUID = 6749953235408513076L;
    @ApiModelProperty("组织架构ID<集合>")
    private Set<String> orgIds;
    @ApiModelProperty("组织架构名称")
    private String orgName;
    @ApiModelProperty("组名/团队名称 别名")
    private String groupInfoName;
    @ApiModelProperty("组别状态")
    private Integer status;
    @ApiModelProperty("负责人账户ID<集合>")
    private Set<String> accountIds;
}
