package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInstanceSearchVO implements Serializable {
    private static final long serialVersionUID = 4906326548737905763L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID<集合>", notes = "批量精确匹配")
    private Set<String> accountIds;
    @ApiModelProperty(value = "账号名", notes = "左右模糊匹配")
    private String accountName;
    @ApiModelProperty(value = "应用 id", notes = "精确匹配")
    private String appId;
    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;
    /**
     * todo 可能还有跨表的字段需求
     */
    @ApiModelProperty(value = "角色ID", notes = "来源<account_role>与<rbac_role>表 <column>id  ")
    private String roleId;
    @ApiModelProperty(value = "机构ID", notes = "来源<account_org>与<account_group>表 <column>org_id")
    private String orgId;
}
