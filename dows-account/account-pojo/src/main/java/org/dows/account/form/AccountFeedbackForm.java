package org.dows.account.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账号-反馈(实名认证后)(AccountFeedback)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:30
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountFeedbackForm 表单对象", description = "账号-反馈(实名认证后)")
public class AccountFeedbackForm implements Serializable {
    private static final long serialVersionUID = 780642613565779406L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    /** 反馈图片*/
    @ApiModelProperty(name = "反馈图片")
    private String feedbackImgs;

    /** 联系人姓名 */
    @ApiModelProperty(name = "联系人姓名")
    private String feedbackName;

    /** 联系人电话 */
    @ApiModelProperty(name = "联系人电话")
    private String feedbackPhone;

    @JsonIgnore
    private Boolean deleted;


}

