package org.dows.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/8 13:49
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserEducationVo对象", description = "用户-教育信息")
public class UserEducationVo {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("教育程度[小学|中学|高中|大学|...]")
    private String degree;

    @ApiModelProperty("学校|机构名称")
    private String orgName;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;

}
