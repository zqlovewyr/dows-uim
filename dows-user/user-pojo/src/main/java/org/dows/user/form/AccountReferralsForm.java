package org.dows.user.form;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐关系(AccountReferrals)表单
 *
 * @author lait.zhang
 * @since 2022-10-22 10:07:28
 */
@SuppressWarnings("serial")
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountReferralsForm 表单对象", description = "推荐关系")
public class AccountReferralsForm implements Serializable {
    private static final long serialVersionUID = 213738462185013949L;
    @JsonIgnore
    private Long id;

    @ApiModelProperty("父节点ID（数据库自增）, 没有父节点则为 -1")
    private Long pid;

    @ApiModelProperty("组")
    private Long org;

    @ApiModelProperty("顺序")
    private Long seq;

    @ApiModelProperty("父节点顺序: 3,4,5,6(对应: deep-4, deep-3, deep-2, deep-1)")
    private String pseq;

    @ApiModelProperty("seq 向量")
    private String vector;

    @ApiModelProperty("推荐账号ID, 没有推荐人则为 -1")
    private String fai;

    @ApiModelProperty("推荐账号名")
    private String fan;

    @ApiModelProperty("推荐账号手机")
    private String fap;

    @ApiModelProperty("被推荐账号ID")
    private String tai;

    @ApiModelProperty("被推荐账号名")
    private String tan;

    @ApiModelProperty("被推荐账号手机")
    private String tap;

    @ApiModelProperty("应用ID")
    private String appId;

    @JsonIgnore
    private Boolean deleted;


}

