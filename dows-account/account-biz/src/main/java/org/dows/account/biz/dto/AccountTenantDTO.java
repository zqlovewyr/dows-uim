package org.dows.account.biz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.framework.crud.mybatis.CrudEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 账号-租户(AccountTenant)实体类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:54
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountTenant对象", description = "账号-租户")
public class AccountTenantDTO implements Serializable {
    private static final long serialVersionUID = -65033477464376957L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商户号")
    private String merchantNo;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;

}

