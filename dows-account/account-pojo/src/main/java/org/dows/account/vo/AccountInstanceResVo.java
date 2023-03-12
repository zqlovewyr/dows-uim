package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.account.util.MyConstant;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-实例维度信息(AccountInstance)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstance对象", description = "账号-实例维度信息")
public class AccountInstanceResVo implements Serializable {
    private static final long serialVersionUID = -43366571366151809L;

    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "账号ID")
    private String accountId;

    @ApiModelProperty(value = "账号名")
    private String accountName;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "性别Str")
    private String sexStr;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "身份证号")
    private String identifier;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "门店ID")
    private String storeId;

    @ApiModelProperty(value = "门店名称")
    private String storeName;
    @ApiModelProperty(value = "部门名称")
    private String orgName;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "入职时间")
    private Date entryTime;

    @ApiModelProperty(value = "工作时长")
    private String jobHours;

    @ApiModelProperty(value = "时间戳/创建时间")
    @JsonFormat(pattern = MyConstant.DEFAULT_DATE_FORMAT)
    private Date createTime;

}

