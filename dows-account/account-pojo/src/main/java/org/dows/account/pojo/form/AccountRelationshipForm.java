package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-关系维度信息(AccountRelationship)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:41
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRelationship对象", description = "账号-关系维度信息")
public class AccountRelationshipForm implements Serializable {
    private static final long serialVersionUID = 226565459952334577L;

    @ApiModelProperty(value = "父节点ID（数据库自增）, 没有父节点则为 -1")
    private Long pid;
    @ApiModelProperty(value = "组")
    private Integer org;
    @ApiModelProperty(value = "深度")
    private Integer deep;
    @ApiModelProperty(value = "顺序")
    private Integer seq;
    @ApiModelProperty(value = "父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)")
    private String pseq;
    @ApiModelProperty(value = "seq 向量")
    private String vector;
    @ApiModelProperty(value = "推荐账号ID, 没有推荐人则为 -1")
    private Long fromAccountId;
    @ApiModelProperty(value = "推荐账号名")
    private String fromAccountName;
    @ApiModelProperty(value = "推荐账号手机")
    private String fromAccountPhone;
    @ApiModelProperty(value = "推荐用户ID")
    private String formUserId;
    @ApiModelProperty(value = "被推荐账号ID")
    private String toAccountId;
    @ApiModelProperty(value = "被推荐账号名")
    private String toAccountName;
    @ApiModelProperty(value = "被推荐账号手机")
    private String toAccountPhone;
    @ApiModelProperty(value = "被推荐用户ID")
    private String toUserId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
}

