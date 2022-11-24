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
 * rbac-url(RbacUri)实体类
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
@ApiModel(value = "RbacUri对象", description = "rbac-url")
public class RbacUri implements CrudEntity {
    private static final long serialVersionUID = -95815315380355552L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("资源路径")
    private String uriPath;

    @ApiModelProperty("资源描述")
    private String uriDescr;

    @ApiModelProperty("业务code")
    private String bizCode;

    @ApiModelProperty("pageID")
    private String pageId;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("应用名称")
    private String appName;

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

