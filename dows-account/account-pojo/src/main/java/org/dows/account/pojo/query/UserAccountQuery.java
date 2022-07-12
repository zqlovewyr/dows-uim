package org.dows.account.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-账号维度信息(UserAccount)Query类
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
public class UserAccountQuery implements Serializable {
    private static final long serialVersionUID = -98991512119019507L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "账号ID")
    private Long accountId;
    @ApiModelProperty(value = "应用ID")
    private String appId;
    @ApiModelProperty(value = "时间戳")
    private Date dt;
}

