package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 账号-账号组维度信息(AccountGroup)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:19
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroup对象", description = "账号-账号组维度信息")
public class AccountGroup implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库主键自增ID")
    private Long id;
    @ApiModelProperty("分布式ID")
    private String groupId;
    @ApiModelProperty("组织架构ID")
    private String orgId;
    @ApiModelProperty("岗位名")
    private String orgName;
    @ApiModelProperty("岗位code")
    private String orgCode;
    @ApiModelProperty("icon")
    private String orgIcon;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("账号ID")
    private String accountId;
    @ApiModelProperty("账号名")
    private String accountName;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("用户真实姓名")
    private String userName;
    @ApiModelProperty("用户邮箱")
    private String userEmail;
    @ApiModelProperty("用户电话")
    private String userPhone;
    @ApiModelProperty("组编号|工号")
    private Integer groupNo;
    @ApiModelProperty("描述")
    private String descr;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
