package org.dows.account.crud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.util.Date;

/**
 * 账号-关系维度信息(AccountRelationships)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:34
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountRelationships对象", description = "账号-关系维度信息")
public class AccountRelationships implements CrudEntity {
    @ApiModelProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("父节点ID（数据库自增）, 没有父节点则为 -1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long pid;
    @ApiModelProperty("组")
    private Integer org;
    @ApiModelProperty("深度")
    private Integer deep;
    @ApiModelProperty("顺序")
    private Integer seq;
    @ApiModelProperty("父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)")
    private String pseq;
    @ApiModelProperty("seq 向量")
    private String vector;
    @ApiModelProperty("推荐账号ID, 没有推荐人则为 -1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long fromAccountId;
    @ApiModelProperty("推荐账号名")
    private String fromAccountName;
    @ApiModelProperty("推荐账号手机")
    private String fromAccountPhone;
    @ApiModelProperty("被推荐账号ID")
    private String toAccountId;
    @ApiModelProperty("被推荐账号名")
    private String toAccountName;
    @ApiModelProperty("被推荐账号手机")
    private String toAccountPhone;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}
