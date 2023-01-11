package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.account.util.MyConstant;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-实例维度信息(AccountInstance)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstance对象", description = "账号-实例维度信息")
public class AccountInstanceQuery implements Serializable {
    private static final long serialVersionUID = -68886389339514945L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String accountName;

    @ApiModelProperty("手机号")
    private String phone;


    @ApiModelProperty("性别 1、男2、女3、未知")
    private Integer gender;

    @ApiModelProperty("所属门店ID")
    private String storeId;

    @ApiModelProperty("所属部门ID")
    private String orgId;

    @ApiModelProperty("职位")
    private String job;


    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("角色")
    private String roleId;

    @ApiModelProperty("入职开始日期")
    @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT)
    private Date entryDateStart;
    @ApiModelProperty("入职结束日期")
    @DateTimeFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT)
    private Date entryDateEnd;


}

