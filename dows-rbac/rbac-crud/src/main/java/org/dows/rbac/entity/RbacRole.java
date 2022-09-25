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
 * RBAC-角色(RbacRole)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:22
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacRole对象", description = "RBAC-角色")
public class RbacRole implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("账号角色ID")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("角色父id 角色组")
    private Long rolePid;
    @ApiModelProperty("角色名")
    private String roleName;
    @ApiModelProperty("角色code")
    private String roleCode;
    @ApiModelProperty("名称首字母")
    private String nameLetter;
    @ApiModelProperty("角色级别")
    private Integer roleLevel;
    @ApiModelProperty("角色icon")
    private String roleIcon;
    @ApiModelProperty("角色组数量")
    private Integer subCount;
    @ApiModelProperty("数据权限")
    private String dataScope;
    @ApiModelProperty("顺序")
    private Integer sorted;
    @ApiModelProperty("描述")
    private String descr;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("租户ID")
    private Long tenantId;
    @ApiModelProperty("应用ID")
    private String appId;
    @JsonIgnore
    private Integer ver;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
}
