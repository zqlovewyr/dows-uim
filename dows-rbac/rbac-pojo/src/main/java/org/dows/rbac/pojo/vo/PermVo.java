package org.dows.rbac.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PermVo {
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    //权限名称
    @ApiModelProperty("权限名称")
    private String permissionName;
    //权限码
    @ApiModelProperty("权限码")
    private String permissionCode;
    //权限ICON
    private String permissionIcon;
    //资源ID
    private Long resourceId;
    //资源父ID
    private Long resourcePid;
    //资源名
    private String resourceName;
    @ApiModelProperty("1目录, 2菜单, 3按钮, 4链接")
    //类型: 1目录, 2菜单, 3按钮, 4链接
    private Integer resourceTyp;
    //资源值
    private String resourceVal;
    //资源路径
    @ApiModelProperty("资源路径")
    private String resourcePath;
    //资源图标
    private String resourceIcon;
    //资源描述
    @ApiModelProperty("资源描述")
    private String resourceDescr;
    //是否隐藏: 0不隐藏, 1隐藏. 默认: 0
    private Boolean visible;
    //租户ID
    private Long tenantId;
    //应用 id
    private String appId;
    //乐观锁, 默认: 0
    private Integer ver;

    //时间戳/创建时间
    private Date dt;
    @ApiModelProperty("产品ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long productId;

    @ApiModelProperty("产品类型ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    @ApiModelProperty("是否平台 0 租户 1 平台")
    private Integer isPlatform;
    @ApiModelProperty("父选项名")
    private String parentName;
    @ApiModelProperty("资源产品")
    private String productName;
    @ApiModelProperty("资源类目")
    private String categoryName;


}
