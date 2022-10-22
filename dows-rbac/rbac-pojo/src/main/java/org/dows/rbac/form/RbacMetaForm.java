package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-元数据(RbacMeta)表单
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
@ApiModel(value = "RbacMetaForm 表单对象", description = "rbac-元数据")
public class RbacMetaForm implements Serializable {
    private static final long serialVersionUID = -58567995548036781L;
    @JsonIgnore
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
    private Boolean deleted;


}

