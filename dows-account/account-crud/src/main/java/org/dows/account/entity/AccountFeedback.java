package org.dows.account.entity;

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
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 账号-反馈(实名认证后)(AccountFeedback)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountFeedback对象", description = "账号-反馈(实名认证后)")
public class AccountFeedback implements CrudEntity {
    private static final long serialVersionUID = 5485651946034818845L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

