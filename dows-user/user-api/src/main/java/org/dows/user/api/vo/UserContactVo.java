package org.dows.user.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/1 18:36
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserContactVo对象", description = "用户-联系方式")
public class UserContactVo {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("联系号码")
    private String contactNum;

    @ApiModelProperty("联系类型（0:手机，1:邮箱，2:电话）")
    private Integer contactType;

    @ApiModelProperty("排序")
    private Integer sorted;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("是否是自己")
    private Boolean self;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("时间戳")
    private Date dt;
}
