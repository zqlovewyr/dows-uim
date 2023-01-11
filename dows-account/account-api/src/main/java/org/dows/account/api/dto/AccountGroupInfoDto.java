package org.dows.account.api.dto;

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
 * @date 2023/1/9 17:44
 */
@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountGroupInfo对象", description = "账号-组信息")
public class AccountGroupInfoDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("组织架构ID")
    private String orgId;

    @ApiModelProperty("组别ID")
    private String groupId;

    @ApiModelProperty("组名/团队名称")
    private String groupName;

    @ApiModelProperty("负责人名称")
    private String owner;

    @ApiModelProperty("负责人电话")
    private Boolean ownerPhone;

    @ApiModelProperty("所在区域")
    private String district;

    @ApiModelProperty("团队地址")
    private String address;

    @ApiModelProperty("团队描述")
    private String descr;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除")
    private Boolean deleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date dt;

}
