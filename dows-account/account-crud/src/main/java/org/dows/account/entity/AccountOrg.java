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

import java.util.Date;

/**
 * 账号-组织架构(AccountOrg)实体类
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
@ApiModel(value = "AccountOrg对象", description = "账号-组织架构")
public class AccountOrg implements CrudEntity {
    private static final long serialVersionUID = -90182493217782271L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID(pid空时为总店)")
    private Long pid;

    @ApiModelProperty("实体域组织机构ID")
    private String orgId;

    @ApiModelProperty("组织名称")
    private String orgName;

    @ApiModelProperty("名称首字母")
    private String nameLetters;

    @ApiModelProperty("组织code")
    private String orgCode;

    @ApiModelProperty("头像")
    private String profile;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("组织描述")
    private String descr;

    @ApiModelProperty("组织类型")
    private String orgType;

    @ApiModelProperty("排序")
    private Integer sorted;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("手机号")
    private String phone;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

    @ApiModelProperty("允许最大成员数")
    private Integer maxNumber;

    @ApiModelProperty("有效时间")
    private Date indate;

    @ApiModelProperty("失效时间")
    private Date expdate;

}

