package org.dows.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/8 13:29
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserExtinfoDTO对象", description = "用户-扩展信息")
public class UserExtinfoDTO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("性格[多个,分割]")
    private String character;

    @ApiModelProperty("特长[多个,分割]")
    private String features;

    @ApiModelProperty("简介")
    private String intro;

    @ApiModelProperty("婚姻状态[0：未婚，1：已婚]")
    private Boolean married;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;
}
