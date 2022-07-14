package org.dows.rbac.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RoleVo {

    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 角色父id 角色组
     */
    private Long rolePid;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;

    /**
     * 角色code
     */
    @ApiModelProperty("角色code")
    private String roleCode;

    /**
     * 角色icon
     */
    private String icon;

    /**
     * 顺序
     */
    private Integer sorted;

    /**
     * 描述
     */
    private String descr;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID 0为平台 其余为其他租户")
    private Long tenantId;
    @ApiModelProperty("租户名")
    private String tenantName;

    /**
     * 乐观锁, 默认: 0
     */
    private Integer ver;


    /**
     * 时间戳/创建时间
     */
    private Date dt;
    @ApiModelProperty("角色类型 0 租户 1 平台")
    private Integer isPlatform;
    private Long principalId;
}
