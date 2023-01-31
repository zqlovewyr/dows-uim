package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class AccountInstanceVo implements Serializable {
    private static final long serialVersionUID = -43366571366151809L;

    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private Long accountId;

    @ApiModelProperty(value = "账号名")
    private String accountName;

    @ApiModelProperty(value = "账号密码")
    private String accountPwd;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("所属机构名称")
    private String orgName;

    @ApiModelProperty("组别ID")
    private String groupInfoId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "应用 id")
    private String tenantId;

    @ApiModelProperty(value = "状态，锁定/异常等 ")
    private Integer status;

    @ApiModelProperty(value = "来源, 推广统计用")
    private String source;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;


}

