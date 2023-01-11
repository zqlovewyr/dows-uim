package org.dows.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/9 17:44
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroupInfoDTO对象", description = "账号-组负责人信息")
public class AccountGroupInfoDTO {

    @ApiModelProperty("组别ID")
    private String groupId;

    @ApiModelProperty("组名/团队名称")
    private String groupName;

    @ApiModelProperty("账户ID")
    private String accountId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("负责人名称")
    private String owner;

    @ApiModelProperty("负责人电话")
    private String ownerPhone;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String descr;

    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private Date dt;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
