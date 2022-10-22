package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-数据规则(RbacRule)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:19
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacRuleForm 表单对象", description = "rbac-数据规则")
public class RbacRuleForm implements Serializable {
    private static final long serialVersionUID = 211894790844647886L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("父ID")
    private Long pid;

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
    private Boolean deleted;


}

