package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-实例维度信息(AccountInstance)Query类
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
public class AccountInstanceQuery implements Serializable {
    private static final long serialVersionUID = -68886389339514945L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;
    @ApiModelProperty(value = "账号名")
    private String accountName;
    @ApiModelProperty(value = "账号密码")
    private String accountPwd;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "应用 id")
    private String tenantId;
    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;
    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}

