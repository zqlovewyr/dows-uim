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
 * rbac-权限(RbacPermission)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:46
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacPermission对象", description = "rbac-权限")
public class RbacPermission implements CrudEntity {
    private static final long serialVersionUID = -12891072856577844L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("角色ID")
    private Long roleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父角色ID(继承时该字段有值)")
    private Long rolePid;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("资源ID")
    private Long uriId;

    @ApiModelProperty("角色CODE")
    private String roleCode;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("权限码")
    private String authority;

    @ApiModelProperty("uri路径")
    private String uriPath;

    @ApiModelProperty("功能描述")
    private String uriDescr;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("是否隐藏: 0不隐藏, 1隐藏. 默认: 0")
    private Boolean visible;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

