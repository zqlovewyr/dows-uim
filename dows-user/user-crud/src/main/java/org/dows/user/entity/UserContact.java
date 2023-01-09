package org.dows.user.entity;

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
 * 用户-联系人(UserContact)实体类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:14
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "UserContact对象", description = "用户-联系人")
public class UserContact implements CrudEntity {
    private static final long serialVersionUID = 960407450586745777L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("联系号码")
    private String contactNum;

    @ApiModelProperty("联系类型（0:手机，1:邮箱，2:电话）")
    private Integer contactTyp;

    @ApiModelProperty("排序")
    private Integer sorted;

    @ApiModelProperty("状态")
    private Integer state;

    @ApiModelProperty("是否是自己")
    private Boolean self;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("时间戳")
    private Date dt;

}

