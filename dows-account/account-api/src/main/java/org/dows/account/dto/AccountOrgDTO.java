package org.dows.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrgDTO对象", description = "账号-组织架构")
public class AccountOrgDTO {

    @ApiModelProperty(value = "主键Id")
    private String id;

    @ApiModelProperty("映射账户ID")
    private String mappingId;

    @ApiModelProperty("主键ID集合")
    private Set<String> ids;

    @ApiModelProperty(value = "父ID(父级组织ID)")
    private Long pid;

    @ApiModelProperty("实体域组织机构ID")
    private String orgId;

    @ApiModelProperty(value = "组织名称", required = true)
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织code")
    private String orgCode;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty(value = "应用ID", required = true)
    private String appId;

    @ApiModelProperty(value = "租户ID", required = true)
    private String tenantId;

    @ApiModelProperty(value = "角色Id rbac-role", example = "传值表示组织成员全部拥有对应角色权限")
    private Long rbacRoleId;

    @ApiModelProperty("组织类型")
    private Integer orgType;

    @ApiModelProperty("排序")
    private Integer sorted;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("组织成员集合")
    private List<AccountOrgGroupDTO> accountOrgGroups;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("负责人名称")
    private String owner;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty("创建时间")
    private Date dt;

    @ApiModelProperty("机构名称排序方式")
    private String orgNameType;

    @ApiModelProperty("机构编码排序方式")
    private String orgCodeType;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

}
