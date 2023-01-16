package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-职业用户关联表(AccountPostUser)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:39
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountPostUser对象", description = "账号-职业用户关联表")
public class AccountPostUserVo implements Serializable {
    private static final long serialVersionUID = -43366571366151809L;

    @ApiModelProperty(value = "账号-标识 ID")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "账号ID")
    private String accountId;

    @ApiModelProperty(value = "职位ID")
    private Long postId;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;

    @ApiModelProperty(value = "是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;

}

