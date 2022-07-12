package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户-实体维度信息(UserInstance)DTO类
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
public class UserInstanceDto implements Serializable {
    private static final long serialVersionUID = 308438571011372170L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNo;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private String age;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private Date birthday;
    /**
     * 名族
     */
    @ApiModelProperty(value = "名族")
    private String nation;
    /**
     * 发证机关
     */
    @ApiModelProperty(value = "发证机关")
    private String signOrg;
    /**
     * 户籍地址
     */
    @ApiModelProperty(value = "户籍地址")
    private String domicile;
    /**
     * 有效时间（开始时间）
     */
    @ApiModelProperty(value = "有效时间（开始时间）")
    private Date indate;
    /**
     * 失效时间（失效时间）
     */
    @ApiModelProperty(value = "失效时间（失效时间）")
    private Date expdate;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date dt;


}
