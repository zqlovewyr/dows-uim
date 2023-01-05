package org.dows.account.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：wHuan
 * @Date：2022/11/25 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOrg对象", description = "账号-组织架构(树形参数)")
public class TreeAccountOrgDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "父ID(父级组织ID)")
    private Long pid;

    // 是否递推查询
    private Boolean recQuery;
    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织code")
    private String orgCode;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("描述")
    private String descr;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("下层组织对象")
    private List<TreeAccountOrgDTO> nextAccountOrgDto;

}
