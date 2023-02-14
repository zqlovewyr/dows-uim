package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-实体维度信息(UserInstance)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:54
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserInstance对象", description = "用户-实体维度信息")
public class UserInstanceQuery implements Serializable {
    private static final long serialVersionUID = 882298321570659266L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty("头像")
    private String avatar;
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
    @ApiModelProperty(value = "有效时间（开始时间）")
    private Date indate;
    @ApiModelProperty(value = "失效时间（失效时间）")
    private Date expdate;
    @ApiModelProperty(value = "时间戳")
    private Date dt;
}

