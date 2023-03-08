package org.dows.account.dto;

//import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author runsix
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInstanceDTO implements Serializable {
    private static final long serialVersionUID = 4496765086642042824L;
    private String id;
    private String mappingId;
    private String appId;
    private String tenantId;
    /* runsix:field 'identifier' is in class AccountIdentifier. */
//    @ExcelProperty(value = "用户账号(code)")
    private String identifier;
    /* runsix:field 'rbacRoleId' is field 'id' in class RbacRole */
    private Long rbacRoleId;
    private Long roleId;
    /* runsix:field 'accountOrgId' is field 'orgId' in class AccountOrg */
    private String accountOrgOrgId;
    private String accountId;
    private Set<String> accountIds;
//    @ExcelProperty(value = "用户姓名")
    private String accountName;
    private String password;
    private String avatar;
    private String source;
    private String phone;
    private Integer status;
    private String roleName;

    @ApiModelProperty("组别名称")
    private String teamName;

    @ApiModelProperty("账号/姓名/手机号")
    private String accountNamePhone;

    @ApiModelProperty("机构名称")
    private String orgName;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("账户主体类型，0-个人，1-组")
    private Integer principalType;

    @ApiModelProperty("创建时间")
    private Date dt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private Date indate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private Date expdate;
}
