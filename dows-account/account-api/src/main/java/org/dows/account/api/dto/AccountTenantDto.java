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
 * @create: 2022-07-14 21:33:51
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
    private static final long serialVersionUID = -56071612572318557L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "数据库自增主键ID")
    private Long id;
    @ApiModelProperty(value = "账号ID")
    private String accountId;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "顺序")
    private Integer sorted;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
