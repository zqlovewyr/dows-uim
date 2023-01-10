package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/10 9:55
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountUserVo对象", description = "账号-用户实例信息")
public class AccountUserVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "账号id")
    private Long accountId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
