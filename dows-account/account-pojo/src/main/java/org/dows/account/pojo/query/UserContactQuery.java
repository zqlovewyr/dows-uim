package org.dows.account.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-联系维度信息(UserContact)Query类
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
@ApiModel(value = "UserContact对象", description = "用户-联系维度信息")
public class UserContactQuery implements Serializable {
    private static final long serialVersionUID = 505643485621435181L;

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
    @ApiModelProperty(value = "联系方式名称（手机，电话，邮箱等）")
    private String contactName;
    @ApiModelProperty(value = "联系号码")
    private String contactNo;
    @ApiModelProperty(value = "联系类型（0:手机，1:邮箱，2:电话）")
    private Integer contactTyp;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    @ApiModelProperty(value = "时间戳")
    private Date dt;
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
}

