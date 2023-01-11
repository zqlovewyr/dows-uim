package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-组织架构(AccountOrg)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrgForm 表单对象", description = "账号-组织架构")
public class AccountOrgForm implements Serializable {
    private static final long serialVersionUID = -82876957368962100L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("父ID(pid空时为总店)")
    private Long pid;

    @ApiModelProperty("实体域组织机构ID")
    private String orgId;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织code")
    private String orgCode;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("组织类型")
    private String orgTyp;

    @ApiModelProperty("排序")
    private String sorted;

    @ApiModelProperty("状态")
    private String status;

    @JsonIgnore
    private Boolean deleted;


}

