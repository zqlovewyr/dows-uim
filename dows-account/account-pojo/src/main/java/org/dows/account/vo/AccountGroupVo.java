package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 账号-账号组维度信息(AccountGroup)VO类
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroup对象", description = "账号-账号组维度信息")
public class AccountGroupVo implements Serializable {
    private static final long serialVersionUID = 479168695435247827L;

    @ApiModelProperty(value = "主键")
    //主键ID
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "组织架构ID")
    private String orgId;

    @ApiModelProperty(value = "岗位名/组名")
    private String orgName;

    @ApiModelProperty(value = "账号ID")
    private String accountId;

    @ApiModelProperty(value = "账号名称")
    private String accountName;

    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "发证机关")
    private String signOrg;

    @ApiModelProperty(value = "户籍地址")
    private String domicile;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "账户角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "负责人ID")
    private String ownerId;

    @ApiModelProperty(value = "负责人名称")
    private String ownerName;

    @ApiModelProperty(value = "乐观锁")
    private String ver;

    @ApiModelProperty(value = "是否逻辑删除")
    private String deleted;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty(value = "角色权限")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> roleIdList;

}

