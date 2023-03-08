package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2023/1/9 20:57
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountInstanceVo对象", description = "账号-实例维度信息")
public class AccountInstanceVo implements Serializable {

    private static final long serialVersionUID = -43366571366151809L;

    @ApiModelProperty(value = "账号-标识 ID")
    private String id;

    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;

    @ApiModelProperty(value = "账号名")
    private String accountName;

    @ApiModelProperty(value = "账号密码")
    private String accountPwd;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "应用 id")
    private String tenantId;

    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty(value = "账号密码")
    private String password;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("所属机构ID")
    private String orgId;

    @ApiModelProperty("所属机构名称")
    private String orgName;

    @ApiModelProperty("组别ID")
    private String groupInfoId;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;

    @ApiModelProperty("账号绑定的唯一手机号(可更换)")
    private String phone;
}
