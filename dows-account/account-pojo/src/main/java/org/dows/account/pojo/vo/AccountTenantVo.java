package org.dows.account.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-租户维度信息(AccountTenant)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTenant对象", description = "账号-租户维度信息")
public class AccountTenantVo implements Serializable {
    private static final long serialVersionUID = -38555249818885589L;

    @ApiModelProperty(value = "账号角色ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "账号ID")
    private Long accountId;

    @ApiModelProperty(value = "账号名")
    private String accountName;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "商户号")
    private String merchantNo;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "顺序")
    private Integer sorted;


    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    private Long roleId;
    private String roleName;


}

