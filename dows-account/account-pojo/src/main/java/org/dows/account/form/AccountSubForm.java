package org.dows.account.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 子账号(AccountSub)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountSub对象", description = "子账号")
public class AccountSubForm implements Serializable {
    private static final long serialVersionUID = 471265538286310949L;

    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("父账号ID")
    private String parentId;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
//    @NotBlank(message = "账号名不能为空！")
    private String accountName;

    @ApiModelProperty("账号密码")
//    @NotBlank(message = "账号密码不能为空！")
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("性别 1男2女")
    private Integer sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("门店ID")
    private String storeId;

    @ApiModelProperty("门店名称")
    private String storeName;

    @ApiModelProperty("部门id")
    private String deptId;

    @ApiModelProperty("职位id")
    private String positionId;

    @ApiModelProperty("入职时间")
    @JsonFormat(pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date entryTime;

}

