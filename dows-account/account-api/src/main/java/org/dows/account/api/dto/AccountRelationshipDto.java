package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-关系维度信息(AccountRelationship)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:40
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRelationship对象", description = "账号-关系维度信息")
public class AccountRelationshipDto implements Serializable {
    private static final long serialVersionUID = 524332773795442009L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
