package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-数据约束(RbacRegulate)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:18
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacRegulateForm 表单对象", description = "rbac-数据约束")
public class RbacRegulateForm implements Serializable {
    private static final long serialVersionUID = 297594624922529892L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("URI ID")
    private Long uriId;

    @ApiModelProperty("是否允许访问或是否有限制")
    private Boolean allowed;

    @ApiModelProperty("接口版本")
    private String apiVer;

    @ApiModelProperty("secretKEY")
    private String secretKey;

    @ApiModelProperty("ip地址集合")
    private String ips;

    @ApiModelProperty("IP属地")
    private String ipRegion;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("账号ID")
    private String acountId;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("范围控制,作用在(IP,IP_REGION,APP_ID,TENANT_ID...)")
    private String scopes;

    @JsonIgnore
    private Boolean deleted;


}

