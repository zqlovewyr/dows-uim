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
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 子账号(AccountSub)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountSub对象", description = "子账号")
public class AccountSub implements CrudEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 471265538286310949L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("时间戳")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;

    @ApiModelProperty("父账号ID")
    private String parentId;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("账号密码")
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("性别 1男2女")
    private Integer sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("门店ID")
    private String storeId;

    @ApiModelProperty("部门id")
    private String deptId;

    @ApiModelProperty("职位id")
    private String positionId;

    @ApiModelProperty("入职时间")
    @JsonFormat(pattern = "yyyy.MM.dd")
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private Date entryTime;

}

