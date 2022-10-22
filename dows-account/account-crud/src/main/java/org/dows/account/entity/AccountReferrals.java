package org.dows.account.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.framework.crud.mybatis.CrudEntity;

/**
 * 推荐关系(AccountReferrals)实体类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountReferrals对象", description = "推荐关系")
public class AccountReferrals implements CrudEntity {
    private static final long serialVersionUID = 128903798912708122L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("id")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父节点ID（数据库自增）, 没有父节点则为 -1")
    private Long pid;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("组")
    private Long org;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("顺序")
    private Long seq;

    @ApiModelProperty("父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)")
    private String pseq;

    @ApiModelProperty("seq 向量")
    private String vector;

    @ApiModelProperty("推荐账号ID, 没有推荐人则为 -1")
    private String fai;

    @ApiModelProperty("推荐账号名")
    private String fan;

    @ApiModelProperty("推荐账号手机")
    private String fap;

    @ApiModelProperty("被推荐账号ID")
    private String tai;

    @ApiModelProperty("被推荐账号名")
    private String tan;

    @ApiModelProperty("被推荐账号手机")
    private String tap;

    @ApiModelProperty("应用ID")
    private String appId;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}

