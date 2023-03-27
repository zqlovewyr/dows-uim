package org.dows.user.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/8 14:05
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserDwellingDTO对象", description = "用户-住所信息")
public class UserDwellingDTO {

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("主体ID")
    private String principalId;

    @ApiModelProperty("主体类型")
    private String principalType;

    @ApiModelProperty("家庭电话")
    private String telephone;

    @ApiModelProperty("邮政编码")
    private String zipCode;

    @ApiModelProperty("居住面积")
    private String acreage;

    @ApiModelProperty("人均面积")
    private String perarea;

    @ApiModelProperty("房屋类型")
    private String houseType;

    @ApiModelProperty("常住类型")
    private String occupancyType;

    @ApiModelProperty("燃料")
    private String fuel;

    @ApiModelProperty("饮水")
    private String water;

    @ApiModelProperty("社区")
    private String community;

    @ApiModelProperty("厨房排风设施")
    private String exhaustFacility;

    @ApiModelProperty("畜牧栏种类")
    private String rearPoultry;

    @ApiModelProperty("厨房使用方式")
    private String kitchen;

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("逻辑删除")
    private Boolean deleted;
}
