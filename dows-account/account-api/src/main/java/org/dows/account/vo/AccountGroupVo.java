package org.dows.account.vo;

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
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/1/9 20:27
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroupVo对象", description = "账号-组")
public class AccountGroupVo {
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("组织ID")
    private String orgId;

    @ApiModelProperty("组织名")
    private String orgName;

    @ApiModelProperty("组别ID")
    private String groupId;

    @ApiModelProperty("组别名")
    private String groupName;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("账号名")
    private String accountName;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("应用ID")
    private String appId;

    @ApiModelProperty("租户ID")
    private String tenantId;

    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @ApiModelProperty("")
    private Date dt;
}
