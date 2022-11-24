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
 * rbac-数据规则(RbacRule)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:48
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacRule对象", description = "rbac-数据规则")
public class RbacRule implements CrudEntity {
    private static final long serialVersionUID = 941227909405566516L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库自增主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID")
    private Long pid;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("URI ID")
    private Long uriId;

    @ApiModelProperty("数据描述")
    private String ruleDescr;

    @ApiModelProperty("数据表名称")
    private String dataTable;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("角色CODE")
    private String roleCode;

    @ApiModelProperty("应用 id")
    private String appId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

