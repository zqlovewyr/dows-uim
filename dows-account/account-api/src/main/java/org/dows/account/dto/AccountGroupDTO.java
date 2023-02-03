package org.dows.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @description: 账号-账号组维度信息(AccountGroup)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:20
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountGroupDTO对象", description = "账号-账号组维度信息")
public class AccountGroupDTO implements Serializable {
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("主键id集合")
    private Set<String> ids;

    @ApiModelProperty("成员角色ID")
    private Long roleId;

    @ApiModelProperty("团队负责人ID")
    private String ownerId;

    @ApiModelProperty("组织ID")
    private String orgId;

    @ApiModelProperty("组别ID")
    private String groupId;

    @ApiModelProperty("组别名")
    private String groupName;

    @ApiModelProperty("组织ID集合")
    private Set<String> orgIds;

    @ApiModelProperty("组织名")
    private String orgName;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号ID集合")
    private Set<String> accountIds;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String name;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户身份证号")
    private String idNo;

    @ApiModelProperty("用户年龄")
    private String age;

    @ApiModelProperty("用户民族")
    private String nation;

    @ApiModelProperty("发证机关")
    private String signOrg;

    @ApiModelProperty("户籍地址")
    private String domicile;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("创建时间")
    private Date dt;

    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "用户联系方式")
    private String contactNum;

    @ApiModelProperty("用户id集合")
    private Set<String> userIds;

    @ApiModelProperty("上次就诊时间")
    private Date examineTime;

    @ApiModelProperty("建档时间")
    private Date recordTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
