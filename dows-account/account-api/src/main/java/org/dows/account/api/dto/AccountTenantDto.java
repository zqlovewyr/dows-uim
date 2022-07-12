package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-租户维度信息(AccountTenant)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:47
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTenant对象", description = "账号-租户维度信息")
public class AccountTenantDto implements Serializable {
    private static final long serialVersionUID = -71307970124690521L;

    /**
     * 账号角色ID
     */
    @ApiModelProperty(value = "账号角色ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 账号ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号ID")
    private Long accountId;
    /**
     * 用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    private String merchantNo;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
    /**
     * 乐观锁, 默认: 0
     */
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    /**
     * 是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
     */
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    /**
     * 时间戳/创建时间
     */
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;


}
