package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-组织架构(AccountOrg)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrg对象", description = "账号-组织架构")
public class AccountOrgQuery implements Serializable {
    private static final long serialVersionUID = 187775626331310281L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-组ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty("实体域组织机构ID")
    private String orgId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID(pid空时为总店)")
    private Long pid;

    @ApiModelProperty(value = "组code")
    private String orgCode;

    @ApiModelProperty(value = "组织架构名")
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty(value = "头像")
    private String profile;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("组织描述")
    private String descr;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

}

