package org.dows.rbac.entity;

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
 * rbac-角色(RbacRole)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:47
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacRole对象", description = "rbac-角色")
public class RbacRole implements CrudEntity {
    private static final long serialVersionUID = 741113536293101973L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("角色父ID(角色组|继承)")
    private Long pid;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色名称首字母,方便查找")
    private String nameLetters;

    @ApiModelProperty("角色code")
    private String roleCode;

    @ApiModelProperty("角色icon")
    private String roleIcon;

    @ApiModelProperty("角色状态")
    private Integer status;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("当前角色是否继承父角色对应的权限")
    private Boolean inherit;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;
}

