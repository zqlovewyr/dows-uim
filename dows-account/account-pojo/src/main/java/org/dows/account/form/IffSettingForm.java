package org.dows.account.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-实例(AccountInstance)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:26
 */
@SuppressWarnings("serial")
@Data
@ApiModel(value = "IffSettingForm 表单对象", description = "规则设置")
public class IffSettingForm implements Serializable {
    private static final long serialVersionUID = 752274823268208489L;

    /**
     * 规则标题
     */
    @ApiModelProperty("规则标题")
    private String title;

    /**
     * 规则内容体
     */
    @ApiModelProperty("规则内容体")
    private String content;

}

