package org.dows.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2023/1/30 16:56
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserFamilyDTO对象", description = "用户-家庭信息")
public class UserFamilyDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("父ID")
    private String parentId;

    @ApiModelProperty("家庭ID")
    private String familyId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("成员ID[用户ID]")
    private String memberId;

    @ApiModelProperty("关系[父亲|母亲|丈夫|妻子|兄弟|儿子|女儿]")
    private String relation;

    @ApiModelProperty("是否户主[0:否，1：是]")
    private Boolean householder;

    @ApiModelProperty("用户名/身份证号/联系电话/居住地址")
    private String nameNoPhoneAddress;

    @ApiModelProperty("家庭成员姓名")
    private String name;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("婚姻状况")
    private String married;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("职业")
    private String occupation;

    @ApiModelProperty("教育程度")
    private String degree;

    @ApiModelProperty("工作单位")
    private String companyName;

    @ApiModelProperty("居住地址")
    private String address;

    @ApiModelProperty("家庭电话")
    private String telephone;

    @ApiModelProperty("所属社区")
    private String community;

    @ApiModelProperty("居住总面积")
    private String acreage;

    @ApiModelProperty("邮政编码")
    private String zipCode;

    @ApiModelProperty("房屋类型")
    private String houseType;

    @ApiModelProperty("人均居住面积")
    private String perarea;

    @ApiModelProperty("厨房排风设施")
    private String exhaustFacility;

    @ApiModelProperty("厨房使用方式")
    private String kitchen;

    @ApiModelProperty("燃料")
    private String fuel;

    @ApiModelProperty("饮水")
    private String water;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("时间戳")
    private Date dt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("开始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("下三代")
    private List<UserFamilyDTO> children;

    @ApiModelProperty("上三代")
    private List<UserFamilyDTO> parent;

    @ApiModelProperty(value = "页数")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

}
