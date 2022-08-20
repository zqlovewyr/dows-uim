package org.dows.account.crud.entity;

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

import java.util.Date;

/**
 * 账号-关系维度信息(AccountRelationship)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:40
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountRelationship对象", description = "账号-关系维度信息")
public class AccountRelationship implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("id")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父节点ID（数据库自增）, 没有父节点则为 -1")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("推荐账号ID, 没有推荐人则为 -1")
    private String fai;
    @ApiModelProperty("推荐账号名")
    private String fan;
    @ApiModelProperty("推荐账号手机")
    private String fap;
    @ApiModelProperty("推荐用户ID")
    private String fui;
    @ApiModelProperty("被推荐账号ID")
    private String tai;
    @ApiModelProperty("被推荐账号名")
    private String tan;
    @ApiModelProperty("被推荐账号手机")
    private String tap;
    @ApiModelProperty("被推荐用户ID")
    private String tui;
    @ApiModelProperty("应用ID")
    private String appId;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
