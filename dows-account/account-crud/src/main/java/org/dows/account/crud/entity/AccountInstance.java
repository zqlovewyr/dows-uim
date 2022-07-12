package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 账号-实例维度信息(AccountInstance)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:26
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountInstance对象", description = "账号-实例维度信息")
public class AccountInstance implements CrudEntity {
    @ApiModelProperty("账号-标识 ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("账号ID/用户ID/会员ID/商户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    @ApiModelProperty("账号名")
    private String accountName;
    @ApiModelProperty("账号密码")
    private String accountPwd;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("应用 id")
    private String tenantId;
    @ApiModelProperty("状态，锁定/异常等 ")
    private Integer status;
    @ApiModelProperty("来源, 推广统计用")
    private String source;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    private String phone;
    private String email;
    private String employeeNo;
    private String loginName;

}
