package org.dows.user.form;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/2 15:14
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserFamilyForm对象", description = "用户-家庭")
public class UserFamilyForm implements Serializable {

    private static final long serialVersionUID = -72552681460313225L;

    @ApiModelProperty("主键")
    private Long id;

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
}
