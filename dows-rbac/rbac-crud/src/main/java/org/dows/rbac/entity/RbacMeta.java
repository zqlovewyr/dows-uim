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
 * rbac-元数据(RbacMeta)实体类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:17
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacMeta对象", description = "rbac-元数据")
public class RbacMeta implements CrudEntity {
    private static final long serialVersionUID = 545299369053434533L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库自增主键ID")
    private Long id;

    @ApiModelProperty("库表元数据接口")
    private String metadataApi;

    @ApiModelProperty("数据表名称")
    private String dataTable;

    @ApiModelProperty("数据描述")
    private String dataDescr;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("应用名")
    private String appName;

    @ApiModelProperty("应用 id")
    private String appId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}

