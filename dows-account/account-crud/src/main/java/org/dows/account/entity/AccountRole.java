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

/**
 * 账号-角色(AccountRole)实体类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:29
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountRole对象", description = "账号-角色")
public class AccountRole implements CrudEntity {
    private static final long serialVersionUID = 767153227837086623L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色码")
    private String roleCode;

    @ApiModelProperty("主体ID")
    private String principalId;

    @ApiModelProperty("主体名称")
    private String principalName;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}

