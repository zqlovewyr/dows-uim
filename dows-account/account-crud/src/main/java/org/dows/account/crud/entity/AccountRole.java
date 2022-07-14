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
 * 账号-主体角色维度信息(AccountRole)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:41
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountRole对象", description = "账号-主体角色维度信息")
public class AccountRole implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("角色名")
    private String roleName;
    @ApiModelProperty("角色码")
    private String roleCode;
    @ApiModelProperty("主体ID(账号ID/组ID)")
    private String principalId;
    @ApiModelProperty("主体名称")
    private String principalName;
    @ApiModelProperty("主体类型(0：账号，1：组)")
    private Integer principalTyp;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @JsonIgnore
    private Integer ver;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
