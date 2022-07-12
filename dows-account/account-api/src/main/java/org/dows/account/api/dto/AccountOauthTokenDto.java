package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-第三方账号授权(AccountOauthToken)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountOauthToken对象", description = "账号-第三方账号授权")
public class AccountOauthTokenDto implements Serializable {
    private static final long serialVersionUID = -76676054539090146L;

    /**
     * account_oauth_token id
     */
    @ApiModelProperty(value = "account_oauth_token id")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * AccountIdentifierId
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "AccountIdentifierId")
    private Long accountIdentifierId;
    /**
     * 是否支持 refreshToken, 默认: 0. 1 表示支持, 0 表示不支持
     */
    @ApiModelProperty(value = "是否支持 refreshToken, 默认: 0. 1 表示支持, 0 表示不支持")
    private Boolean enableRefresh;
    /**
     * 第三方服务商,如: qq,github
     */
    @ApiModelProperty(value = "第三方服务商,如: qq,github")
    private String providerId;
    /**
     * 第三方用户id
     */
    @ApiModelProperty(value = "第三方用户id")
    private String providerUserId;
    /**
     * accessToken
     */
    @ApiModelProperty(value = "accessToken")
    private String accessToken;
    /**
     * accessToken过期时间 无过期时间默认为 -1
     */
    @ApiModelProperty(value = "accessToken过期时间 无过期时间默认为 -1")
    private Long expireIn;
    /**
     * refreshToken过期时间 无过期时间默认为 -1
     */
    @ApiModelProperty(value = "refreshToken过期时间 无过期时间默认为 -1")
    private Long refreshTokenExpireIn;
    /**
     * refreshToken
     */
    @ApiModelProperty(value = "refreshToken")
    private String refreshToken;
    /**
     * alipay userId
     */
    @ApiModelProperty(value = "alipay userId")
    private String uid;
    /**
     * qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu
     */
    @ApiModelProperty(value = "qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu")
    private String openId;
    /**
     * dingTalk, taobao 附带属性
     */
    @ApiModelProperty(value = "dingTalk, taobao 附带属性")
    private String accessCode;
    /**
     * QQ附带属性
     */
    @ApiModelProperty(value = "QQ附带属性")
    private String unionId;
    /**
     * Google附带属性
     */
    @ApiModelProperty(value = "Google附带属性")
    private String scope;
    /**
     * Google附带属性
     */
    @ApiModelProperty(value = "Google附带属性")
    private String tokenType;
    /**
     * Google附带属性
     */
    @ApiModelProperty(value = "Google附带属性")
    private String idToken;
    /**
     * 小米附带属性
     */
    @ApiModelProperty(value = "小米附带属性")
    private String macAlgorithm;
    /**
     * 小米附带属性
     */
    @ApiModelProperty(value = "小米附带属性")
    private String macKey;
    /**
     * 企业微信附带属性
     */
    @ApiModelProperty(value = "企业微信附带属性")
    private String code;
    /**
     * Twitter附带属性
     */
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthToken;
    /**
     * Twitter附带属性
     */
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthTokenSecret;
    /**
     * Twitter附带属性
     */
    @ApiModelProperty(value = "Twitter附带属性")
    private Long userId;
    /**
     * Twitter附带属性
     */
    @ApiModelProperty(value = "Twitter附带属性")
    private String screenName;
    /**
     * Twitter附带属性
     */
    @ApiModelProperty(value = "Twitter附带属性")
    private String oauthCallbackConfirmed;
    /**
     * 过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1
     */
    @ApiModelProperty(value = "过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1")
    private Long expireTime;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
