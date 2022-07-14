package org.dows.rbac.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GroupVo {

    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty("组织架构名/组名编码")
    private String orgCode;
    @ApiModelProperty("组织架构名/组名")
    private String orgName;
    @ApiModelProperty("租户ID")
    private Long tenantId;

    @ApiModelProperty("租户名称")
    private String tenantName;
    @ApiModelProperty("时间戳")
    private Date dt;
    @ApiModelProperty("用户数")
    private Long userCount;
    @ApiModelProperty("描述")
    private String descr;
    @ApiModelProperty("状态  0 启用 1禁用")
    private Integer status;

}
