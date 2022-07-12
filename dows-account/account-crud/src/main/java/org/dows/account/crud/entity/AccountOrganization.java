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
 * 账号-组织架构维度信息(AccountOrganization)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-15 14:23:05
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
    @ApiModelProperty("账号-权限ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("父ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orgPid;
    @ApiModelProperty("组code")
    private String orgCode;
    @ApiModelProperty("组织架构名")
    private String orgName;
    @ApiModelProperty("组织类型(0:group组,1:element元素)")
    private Integer orgTyp;
    @ApiModelProperty("组织架构ICON")
    private String orgIcon;
    @ApiModelProperty("排序")
    private Integer sorted;
    @ApiModelProperty("租户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("描述")
    private String descr;

}
