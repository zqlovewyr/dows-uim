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
 * 账号-组织架构维度信息(AccountOrganization)表实体类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 21:33:37
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountOrganization对象", description = "账号-组织架构维度信息")
public class AccountOrganization implements CrudEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库自增ID")
    private Long id;
    @ApiModelProperty("分布式ID")
    private String orgId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("父ID")
    private Long orgPid;
    @ApiModelProperty("组织架构名")
    private String orgName;
    @ApiModelProperty("组code")
    private String orgCode;
    @ApiModelProperty("名称首字母")
    private String nameLetter;
    @ApiModelProperty("组织类型(0:group组,1:element元素)")
    private Integer orgType;
    @ApiModelProperty("组织架构ICON")
    private String orgIcon;
    @ApiModelProperty("排序")
    private Integer sorted;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("关联的主体公司")
    private String companyId;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("描述")
    private String descr;
    @JsonIgnore
    private Integer ver;
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;
}
