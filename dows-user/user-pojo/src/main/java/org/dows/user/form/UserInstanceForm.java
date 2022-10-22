package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户-实例(UserInstance)表单
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
@ApiModel(value = "UserInstanceForm 表单对象", description = "用户-实例")
public class UserInstanceForm implements Serializable {
    private static final long serialVersionUID = 634662199181072019L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("名族")
    private String nation;

    @ApiModelProperty("发证机关")
    private String signOrg;

    @ApiModelProperty("户籍地址")
    private String domicile;


}

