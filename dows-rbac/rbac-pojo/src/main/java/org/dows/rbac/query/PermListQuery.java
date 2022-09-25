package org.dows.rbac.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dows.framework.crud.api.PageQuery;

@Data
public class PermListQuery extends PageQuery {
    private String permissionName;
    private String permissionCode;
    @ApiModelProperty("1目录, 2菜单, 3按钮, 4链接")
    private Integer resourceTyp;

    private Long categoryId;
    private Integer isPlatform;
    private String resourcePath;
}
