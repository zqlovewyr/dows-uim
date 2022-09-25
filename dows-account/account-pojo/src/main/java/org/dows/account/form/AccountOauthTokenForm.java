package org.dows.account.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-第三方账号授权(AccountOauthToken)Form类
 * @author: VX:PN15855012581
 * @create: 2022-07-14 21:33:34
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOauthToken对象", description = "账号-第三方账号授权")
public class AccountOauthTokenForm implements Serializable {
    private static final long serialVersionUID = 368552501384173501L;

    @ApiModelProperty(value = "AccountIdentifierId")
    private Long accountIdentifierId;
    @ApiModelProperty(value = "是否支持 refreshToken, 默认: 0. 1 表示支持, 0 表示不支持")
    private Boolean enableRefresh;
    @ApiModelProperty(value = "第三方服务商,如: qq,github")
    private String providerId;
    @ApiModelProperty(value = "第三方用户id")
    private String providerUserId;
    @ApiModelProperty(value = "accessToken")
    private String accessToken;
    @ApiModelProperty(value = "accessToken过期时间 无过期时间默认为 -1")
    private Long expireIn;
    @ApiModelProperty(value = "refreshToken过期时间 无过期时间默认为 -1")
    private Long refreshTokenExpireIn;
    @ApiModelProperty(value = "refreshToken")
    private String refreshToken;
    @ApiModelProperty(value = "alipay userId")
    private String uid;
    @ApiModelProperty(value = "qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu")
    private String openId;
    @ApiModelProperty(value = "dingTalk, taobao 附带属性")
    private String accessCode;
    @ApiModelProperty(value = "企业微信附带属性")
    private String code;
    @ApiModelProperty(value = "过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1")
    private Long expireTime;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    @ApiModelProperty(value = "应用id")
    private String appId;
}

