package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-第三方账号授权(AccountOauthToken)Form类
 * @author: VX:PN15855012581
 * @create: 2021-09-10 15:41:30
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
    private static final long serialVersionUID = -30552649852956570L;

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
    @ApiModelProperty(value = "QQ附带属性")
    private String unionId;
    @ApiModelProperty(value = "Google附带属性")
    private String scope;
    @ApiModelProperty(value = "Google附带属性")
    private String tokenType;
    @ApiModelProperty(value = "Google附带属性")
    private String idToken;
    @ApiModelProperty(value = "小米附带属性")
    private String macAlgorithm;
    @ApiModelProperty(value = "小米附带属性")
    private String macKey;
    @ApiModelProperty(value = "企业微信附带属性")
    private String code;
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthToken;
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthTokenSecret;
    @ApiModelProperty(value = "Twitter附带属性")
    private Long userId;
    @ApiModelProperty(value = "Twitter附带属性")
    private String screenName;
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthCallbackConfirmed;
    @ApiModelProperty(value = "过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1")
    private Long expireTime;
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
}

