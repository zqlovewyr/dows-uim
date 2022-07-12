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
 * 账号-用户维度信息(AccountUser)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:37
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
//
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountUser对象", description = "账号-用户维度信息")
public class AccountUser implements CrudEntity {
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("账号id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    @ApiModelProperty("用户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}
