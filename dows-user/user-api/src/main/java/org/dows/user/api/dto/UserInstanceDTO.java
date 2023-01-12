package org.dows.user.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;
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

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("名族")
    private String nation;

    @ApiModelProperty("发证机关")
    private String signOrg;

    @ApiModelProperty("户籍地址")
    private String domicile;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty(value = "生日开始时间")
    private Date birthdayStartTime;

    @ApiModelProperty(value = "生日结束时间")
    private Date birthdayEndTime;

    @ApiModelProperty("用户时间")
    private Date userDate;

    @ApiModelProperty("有效时间（开始时间）")
    private Date indate;

    @ApiModelProperty("失效时间（失效时间）")
    private Date expdate;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    @ApiModelProperty("ID集合")
    private Set<String> ids;

}
