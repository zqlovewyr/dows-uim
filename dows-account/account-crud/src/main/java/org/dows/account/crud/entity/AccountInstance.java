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
 * 账号-实例维度信息(AccountInstance)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:28
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库ID")
    private Long id;
    @ApiModelProperty("分布式ID")
    private String accountId;
    @ApiModelProperty("账号名")
    private String accountName;
    @ApiModelProperty("账号密码")
    private String accountPwd;
    @ApiModelProperty("账号类型")
    private Integer accountType;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("应用 id")
    private String tenantId;
    @ApiModelProperty("状态，锁定/异常等 ")
    private Integer status;
    @ApiModelProperty("来源, 推广统计用")
    private String source;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
