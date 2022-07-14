package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-标识(AccountIdentifier)DTO类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:25
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountIdentifier对象", description = "账号-标识")
public class AccountIdentifierDto implements Serializable {
    private static final long serialVersionUID = -63171975159727089L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "数据库自增ID")
    private Long id;
    @ApiModelProperty(value = "账号ID/用户ID/会员ID/商户ID")
    private String accountId;
    @ApiModelProperty(value = "识别标识:身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；")
    private String identifier;
    @ApiModelProperty(value = "授权凭证【CREDENTIAL】：站内账号是密码、第三方登录是Token；")
    private String credential;
    @ApiModelProperty(value = "标识类型：登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；")
    private Integer identifierType;
    @ApiModelProperty(value = "应用ID")
    private String appId;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    private String source;
    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;
    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;


}
