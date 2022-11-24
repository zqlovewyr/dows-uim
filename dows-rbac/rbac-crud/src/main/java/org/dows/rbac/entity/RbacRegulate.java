package org.dows.rbac.entity;

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
 * rbac-数据约束(RbacRegulate)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:47
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RbacRegulate对象", description = "rbac-数据约束")
public class RbacRegulate implements CrudEntity {
    private static final long serialVersionUID = -51254403158585073L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("数据库自增主键ID")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("URI ID")
    private Long uriId;

    @ApiModelProperty("是否允许访问或是否有限制")
    private Boolean allowed;

    @ApiModelProperty("接口版本")
    private String apiVer;

    @ApiModelProperty("secretKEY")
    private String secretKey;

    @ApiModelProperty("ip地址集合")
    private String ips;

    @ApiModelProperty("IP属地")
    private String ipRegion;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("账号ID")
    private String acountId;

    @ApiModelProperty("应用 id")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("范围控制,作用在(IP,IP_REGION,APP_ID,TENANT_ID...)")
    private String scopes;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

