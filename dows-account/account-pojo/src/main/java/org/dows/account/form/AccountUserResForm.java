package org.dows.account.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表单
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
@ApiModel(value = "表单对象", description = "用户")
public class AccountUserResForm implements Serializable {
    private static final long serialVersionUID = 634662199181072017L;


    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户密码")
    private String userPwd;
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("性别 1、男2、女3、未知")
    private Integer gender;

    @ApiModelProperty("所属门店ID")
    private Long storeId;

    @ApiModelProperty("所属部门ID")
    private Long deptId;

    @ApiModelProperty("职位")
    private Long positionId;

    @ApiModelProperty("入职时间")
    private Date entryTime;

    @ApiModelProperty("用户类型1：管理账号 2：普通用户")
    private Integer userType;


    @ApiModelProperty("用户状态 1为启用 0为禁用")
    private Integer status;

}

