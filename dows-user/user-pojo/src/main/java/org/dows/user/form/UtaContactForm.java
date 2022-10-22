package org.dows.user.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 联系需求发布者(UtaContact)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UtaContactForm 表单对象", description = "联系需求发布者")
public class UtaContactForm implements Serializable {
    private static final long serialVersionUID = 383626508428249579L;
    @JsonIgnore
    private String id;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("编辑时间")
    private Date updateTime;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("需求发布id")
    private String utaId;


}

