package org.dows.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

/**
 * @author Administrator
 * @date 2023/1/10 13:34
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "UserInstanceDTO对象", description = "用户-实例")
public class UserInstanceDTO {

    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("姓名、身份证号或者手机号")
    private String nameNoPhone;

    @ApiModelProperty("账户ID")
    private String accountId;

    @ApiModelProperty("组织ID")
    private String groupId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("用户ID集合")
    private Set<String> userIds;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("名族")
    private String nation;

    @ApiModelProperty("发证机关")
    private String signOrg;

    @ApiModelProperty("户籍地址")
    private String domicile;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生日开始时间")
    private Date birthdayStartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "生日结束时间")
    private Date birthdayEndTime;

    @ApiModelProperty("用户时间")
    private Date userDate;

    @ApiModelProperty("时间戳")
    private Date dt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    @ApiModelProperty("ID集合")
    private Set<String> ids;

}
