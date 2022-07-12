package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-账号维度信息(UserAccount)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:54
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserAccount对象", description = "用户-账号维度信息")
public class UserAccountDto implements Serializable {
    private static final long serialVersionUID = -92792407638794123L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 账号ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "账号ID")
    private Long accountId;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    private String appId;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
