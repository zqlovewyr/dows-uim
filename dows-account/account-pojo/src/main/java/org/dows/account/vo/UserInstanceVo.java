package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-实体维度信息(UserInstance)VO类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:28:41
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserInstance对象", description = "用户-实体维度信息")
public class UserInstanceVo implements Serializable {
    private static final long serialVersionUID = -89103714921924743L;

    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "名族")
    private String nation;

    @ApiModelProperty(value = "发证机关")
    private String signOrg;

    @ApiModelProperty(value = "户籍地址")
    private String domicile;

    @ApiModelProperty(value = "时间戳")
    private Date dt;

}

