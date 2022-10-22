package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-联系人(UserContact)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:12
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserContactForm 表单对象", description = "用户-联系人")
public class UserContactForm implements Serializable {
    private static final long serialVersionUID = -72552681460313225L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("联系方式名称（手机，电话，邮箱等）")
    private String contactName;

    @ApiModelProperty("联系号码")
    private String contactNo;

    @ApiModelProperty("状态")
    private String state;

    @JsonIgnore
    private Boolean deleted;


}

