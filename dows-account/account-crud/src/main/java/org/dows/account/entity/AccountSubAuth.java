package org.dows.account.entity;

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
 * 子账号权限(AccountSubAuth)实体类
 *
 * @author lait.zhang
 * @since 2022-11-11 00:23:41
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountSubAuth对象", description = "子账号权限")
public class AccountSubAuth implements CrudEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = -49597144345104162L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("全部营销权限:1关闭  2开启")
    private Integer authAll;

    @ApiModelProperty("收银台:1关闭  2开启")
    private Integer authCashier;

    @ApiModelProperty("菜退赠损，订单退款:1关闭  2开启")
    private Integer authOrder;

    @ApiModelProperty("报表:1关闭  2开启")
    private Integer authReport;


    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("时间戳")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;

}

