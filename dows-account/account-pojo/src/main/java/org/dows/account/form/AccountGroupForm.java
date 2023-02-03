package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-组(AccountGroup)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:25
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountGroupForm 表单对象", description = "账号-组")
public class AccountGroupForm implements Serializable {
    private static final long serialVersionUID = -76210584057500890L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("组织ID")
    private String orgId;

    @ApiModelProperty("组名")
    private String orgName;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @JsonIgnore
    private Boolean deleted;


}

