package org.dows.account.biz.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInstanceResDTO implements Serializable {
    private static final long serialVersionUID = 4496765086642042826L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String accountName;

    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("性别 1、男2、女3、未知")
    private Integer gender;

    @ApiModelProperty("所属门店ID")
    private String storeId;

    @ApiModelProperty("所属部门ID")
    private String orgId;

    @ApiModelProperty("职位")
    private String job;

    @ApiModelProperty("入职时间")
    private Date entryTime;

    @ApiModelProperty("账号区分：1、总控端2、总部端、3、门店端APP4、小程序用户")
    private Integer accountType;


    @ApiModelProperty("用户状态 1为启用 0为禁用")
    private Integer status;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("应用ID")
    private String appId;
    @ApiModelProperty("生日")
    private Date birthday;

}
