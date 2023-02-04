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
 * 用户-实例(UserInstance)实体类
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
@ApiModel(value = "UserInstance对象", description = "用户-实例")
public class UserInstance implements CrudEntity {
    private static final long serialVersionUID = 571937250175399112L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("名族")
    private String nation;

    @ApiModelProperty("发证机关")
    private String signOrg;

    @ApiModelProperty("户籍地址")
    private String domicile;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("时间戳")
    private Date dt;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

