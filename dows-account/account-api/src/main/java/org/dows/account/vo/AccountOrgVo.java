package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2023/1/9 20:37
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountOrgVo对象", description = "账号-组织架构维度信息")
public class AccountOrgVo {
    @ApiModelProperty(value = "账号-组ID")
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

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty("组织类型")
    private String orgType;

    @ApiModelProperty("排序")
    private String sorted;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("有效时间")
    private Date indate;

    @ApiModelProperty("失效时间")
    private Date expdate;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty(value = "组织下-账号集合")
    private List<AccountGroupVo> accountGroupList;
}
