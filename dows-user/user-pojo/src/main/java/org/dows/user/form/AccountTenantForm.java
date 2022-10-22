package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-租户(AccountTenant)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTenantForm 表单对象", description = "账号-租户")
public class AccountTenantForm implements Serializable {
    private static final long serialVersionUID = 651142649791672247L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商户号")
    private String merchantNo;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @JsonIgnore
    private Boolean deleted;


}

