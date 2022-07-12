package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-联系维度信息(UserContact)DTO类
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
public class UserContactDto implements Serializable {
    private static final long serialVersionUID = -19918860561215400L;

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
     * 联系方式名称（手机，电话，邮箱等）
     */
    @ApiModelProperty(value = "联系方式名称（手机，电话，邮箱等）")
    private String contactName;
    /**
     * 联系号码
     */
    @ApiModelProperty(value = "联系号码")
    private String contactNo;
    /**
     * 联系类型（0:手机，1:邮箱，2:电话）
     */
    @ApiModelProperty(value = "联系类型（0:手机，1:邮箱，2:电话）")
    private Integer contactTyp;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String state;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sorted;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;
    /**
     * 逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除")
    private Boolean deleted;
    private String accountId;
    private String accountName;


}
