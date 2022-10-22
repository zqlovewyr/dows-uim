package org.dows.rbac.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * rbac-日志(RbacLog)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:16
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RbacLogForm 表单对象", description = "rbac-日志")
public class RbacLogForm implements Serializable {
    private static final long serialVersionUID = -35566566592356149L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("rowID")
    private Long rowId;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("应用ID")
    private String tenantId;

    @JsonIgnore
    private Boolean deleted;


}

