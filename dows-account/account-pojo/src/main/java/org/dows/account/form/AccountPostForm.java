package org.dows.account.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 账号-职位(AccountPost)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountPostForm 表单对象", description = "账号-职位")
public class AccountPostForm implements Serializable {
    private static final long serialVersionUID = -82876957368962100L;
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "职位编号")
    private String postCode;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "职位描述")
    private String post_des;


}

