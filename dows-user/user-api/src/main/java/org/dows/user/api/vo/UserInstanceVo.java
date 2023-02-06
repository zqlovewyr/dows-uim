package org.dows.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/10 13:37
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserInstanceVo对象", description = "用户-实例信息")
public class UserInstanceVo {

    @ApiModelProperty("自增主键ID")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

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

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
