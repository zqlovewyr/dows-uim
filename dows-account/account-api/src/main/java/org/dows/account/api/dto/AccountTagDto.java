package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-标签维度信息(AccountTag)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:44
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTag对象", description = "账号-标签维度信息")
public class AccountTagDto implements Serializable {
    private static final long serialVersionUID = -18742759127228483L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "主键")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "用户id")
    private Long accountId;
    @ApiModelProperty(value = "标签类目")
    private String tagCategory;
    @ApiModelProperty(value = "标签名")
    private String tagName;
    @ApiModelProperty(value = "标签色")
    private String tagColor;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
