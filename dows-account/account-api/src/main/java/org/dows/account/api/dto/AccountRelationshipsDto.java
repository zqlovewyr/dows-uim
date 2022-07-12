package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-关系维度信息(AccountRelationships)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:47
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountRelationships对象", description = "账号-关系维度信息")
public class AccountRelationshipsDto implements Serializable {
    private static final long serialVersionUID = 716071261734657917L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 父节点ID（数据库自增）, 没有父节点则为 -1
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "父节点ID（数据库自增）, 没有父节点则为 -1")
    private Long pid;
    /**
     * 组
     */
    @ApiModelProperty(value = "组")
    private Integer org;
    /**
     * 深度
     */
    @ApiModelProperty(value = "深度")
    private Integer deep;
    /**
     * 顺序
     */
    @ApiModelProperty(value = "顺序")
    private Integer seq;
    /**
     * 父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)
     */
    @ApiModelProperty(value = "父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)")
    private String pseq;
    /**
     * seq 向量
     */
    @ApiModelProperty(value = "seq 向量")
    private String vector;
    /**
     * 推荐账号ID, 没有推荐人则为 -1
     */
    @ApiModelProperty(value = "推荐账号ID, 没有推荐人则为 -1")
    private Long fromAccountId;
    /**
     * 推荐账号名
     */
    @ApiModelProperty(value = "推荐账号名")
    private String fromAccountName;
    /**
     * 推荐账号手机
     */
    @ApiModelProperty(value = "推荐账号手机")
    private String fromAccountPhone;
    /**
     * 被推荐账号ID
     */
    @ApiModelProperty(value = "被推荐账号ID")
    private String toAccountId;
    /**
     * 被推荐账号名
     */
    @ApiModelProperty(value = "被推荐账号名")
    private String toAccountName;
    /**
     * 被推荐账号手机
     */
    @ApiModelProperty(value = "被推荐账号手机")
    private String toAccountPhone;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    /**
     * 乐观锁, 默认: 0
     */
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    /**
     * 时间戳/创建时间
     */
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    /**
     * 是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
     */
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
