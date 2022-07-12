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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号-账号组维度信息(AccountGroup)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:23
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroup对象", description = "账号-账号组维度信息")
public class AccountGroup implements CrudEntity {
    @ApiModelProperty("主键")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("组织架构ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orgId;
    @ApiModelProperty("账号ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountId;
    @ApiModelProperty("岗位名")
    private String orgName;
    @ApiModelProperty("岗位code")
    private String orgCode;
    @ApiModelProperty("icon")
    private String orgIcon;
    @ApiModelProperty("描述")
    private BigDecimal descr;
    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty("时间戳/创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}
