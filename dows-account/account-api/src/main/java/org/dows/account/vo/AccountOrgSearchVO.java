package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 机构的查询
 * 请求
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountOrgSearchVO implements Serializable {
    private static final long serialVersionUID = -8799963830407064520L;
    @ApiModelProperty("实体域组织机构ID<集合>")
    private Set<String> orgIds;
    @ApiModelProperty("组织名称")
    private String orgName;
    @ApiModelProperty("应用ID<集合>")
    private Set<String> appIds;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("状态")
    private Integer status;
}
