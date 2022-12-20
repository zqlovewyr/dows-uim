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
 * 用户-工作信息(UserJob)实体类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "UserJob对象", description = "用户-工作信息")
public class UserJob implements CrudEntity {
    private static final long serialVersionUID = -88902236822557792L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("工作单位名称")
    private String orgName;

    @ApiModelProperty("单位[日|月|年]")
    private String unit;

}

