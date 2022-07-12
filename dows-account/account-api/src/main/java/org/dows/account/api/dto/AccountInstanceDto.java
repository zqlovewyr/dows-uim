package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-实例维度信息(AccountInstance)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstance对象", description = "账号-实例维度信息")
public class AccountInstanceDto implements Serializable {
    private static final long serialVersionUID = -19070837952236031L;

    /**
     * 账号-标识 ID
     */
    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 账号ID/用户ID/会员ID/商户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;
    /**
     * 账号名
     */
    @ApiModelProperty(value = "账号名")
    private String accountName;
    /**
     * 账号密码
     */
    @ApiModelProperty(value = "账号密码")
    private String accountPwd;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 应用 id
     */
    @ApiModelProperty(value = "应用 id")
    private String tenantId;
    /**
     * 状态，锁定/异常等
     */
    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;
    /**
     * 来源, 推广统计用
     */
    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;
    /**
     * 乐观锁, 默认: 0
     */
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    /**
     * 时间戳/创建时间
     */
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    /**
     * 是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
     */
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
