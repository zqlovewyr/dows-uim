package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 * @date 2023/1/9 20:57
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstanceVo对象", description = "账号-实例维度信息")
public class AccountInstanceVo {

    @ApiModelProperty(value = "账号-标识 ID")
    private String id;

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
}
