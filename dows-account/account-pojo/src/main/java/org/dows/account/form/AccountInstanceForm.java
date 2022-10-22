package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-实例(AccountInstance)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstanceForm 表单对象", description = "账号-实例")
public class AccountInstanceForm implements Serializable {
    private static final long serialVersionUID = 752274823268208486L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("账号密码")
    private String password;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("注册来源, 推广统计用")
    private String source;

    @ApiModelProperty("账号绑定的唯一手机号(可更换)")
    private String phone;

    @ApiModelProperty("应用ID")
    private String appId;

    @JsonIgnore
    private Boolean deleted;


}

