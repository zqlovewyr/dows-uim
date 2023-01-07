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
 * 用户(User)实体类
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
@ApiModel(value = "Users对象", description = "用户")
public class Users implements CrudEntity {
    private static final long serialVersionUID = 571937250175399113L;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户密码")
    private String userPwd;
    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份证号")
    private String idCard;
    @ApiModelProperty("性别 1、男2、女3、未知")
    private Integer gender;

    @ApiModelProperty("所属门店ID")
    private Long storeId;

    @ApiModelProperty("所属部门ID")
    private Long deptId;

    @ApiModelProperty("职位")
    private Long positionId;

    @ApiModelProperty("入职时间")
    private Date entryTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("最后登录时间")
    private Date loginLast;

    @ApiModelProperty("总登陆次数")
    private Integer loginTotal;

    @ApiModelProperty("用户类型1：管理账号 2：普通用户")
    private Integer userType;


    @ApiModelProperty("用户状态 1为启用 0为禁用")
    private Integer status;

    @ApiModelProperty("创建者")
    private String creator;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}

