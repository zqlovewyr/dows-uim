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
 * 账号-主体角色维度信息(PrincipalRole)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:45:29
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "PrincipalRole对象", description = "账号-主体角色维度信息")
public class PrincipalRole implements CrudEntity {
    @ApiModelProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("角色ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;
    @ApiModelProperty("角色名")
    private String roleName;
    @ApiModelProperty("角色码")
    private String roleCode;
    @ApiModelProperty("主体ID(账号/角色)")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long principalId;
    @ApiModelProperty("主体名称")
    private String principalName;
    @ApiModelProperty("主体类型(0：账号，1：组)")
    private Integer principalTyp;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;

}
