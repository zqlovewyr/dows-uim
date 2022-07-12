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
 * 账号-第三方账号授权(AccountOauthToken)表实体类
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:30
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountOauthToken对象", description = "账号-第三方账号授权")
public class AccountOauthToken implements CrudEntity {
    @ApiModelProperty("account_oauth_token id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty("AccountIdentifierId")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long accountIdentifierId;
    @ApiModelProperty("是否支持 refreshToken, 默认: 0. 1 表示支持, 0 表示不支持")
    private Boolean enableRefresh;
    @ApiModelProperty("第三方服务商,如: qq,github")
    private String providerId;
    @ApiModelProperty("第三方用户id")
    private String providerUserId;
    @ApiModelProperty("accessToken")
    private String accessToken;
    @ApiModelProperty("accessToken过期时间 无过期时间默认为 -1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long expireIn;
    @ApiModelProperty("refreshToken过期时间 无过期时间默认为 -1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long refreshTokenExpireIn;
    @ApiModelProperty("refreshToken")
    private String refreshToken;
    @ApiModelProperty("alipay userId")
    private String uid;
    @ApiModelProperty("qq/mi/toutiao/wechatMp/wechatOpen/weibo/jd/kujiale/dingTalk/douyin/feishu")
    private String openId;
    @ApiModelProperty("dingTalk, taobao 附带属性")
    private String accessCode;
    @ApiModelProperty("QQ附带属性")
    private String unionId;
    @ApiModelProperty("Google附带属性")
    private String scope;
    @ApiModelProperty("Google附带属性")
    private String tokenType;
    @ApiModelProperty("Google附带属性")
    private String idToken;
    @ApiModelProperty("小米附带属性")
    private String macAlgorithm;
    @ApiModelProperty("小米附带属性")
    private String macKey;
    @ApiModelProperty("企业微信附带属性")
    private String code;
    @ApiModelProperty("Twitter附带属性")
    private String oauthToken;
    @ApiModelProperty("Twitter附带属性")
    private String oauthTokenSecret;
    @ApiModelProperty("Twitter附带属性")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
    @ApiModelProperty("Twitter附带属性")
    private String screenName;
    @ApiModelProperty("Twitter附带属性")
    private String oauthCallbackConfirmed;
    @ApiModelProperty("过期时间, 基于 1970-01-01T00:00:00Z, 无过期时间默认为 -1")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long expireTime;
    @ApiModelProperty("租户ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tenantId;
    @ApiModelProperty("时间戳")
    @TableField(fill = FieldFill.INSERT)
    private Date dt;
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
