package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-用户(实名认证后)(AccountUser)表单
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
@ApiModel(value = "AccountUserForm 表单对象", description = "账号-用户(实名认证后)")
public class AccountUserForm implements Serializable {
    private static final long serialVersionUID = 780642613565779406L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tentantId;

    @JsonIgnore
    private Boolean deleted;


}

