package org.dows.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.dows.user.api.dto.UserFamilyDTO;
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
@ApiModel(value = "UserFamilyVo对象", description = "用户-家庭信息")
public class UserFamilyVo {
    @ApiModelProperty("主键")
    private String id;

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

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("户主姓名")
    private String householderName;

    @ApiModelProperty("成员姓名")
    private String memberName;

    @ApiModelProperty("身份证号")
    private String idNo;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("家庭电话")
    private String telephone;

    @ApiModelProperty("社区")
    private String community;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("手机号")
    private String domicile;

    @ApiModelProperty("居住地址")
    private String residential;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("婚姻状况")
    private Integer married;

    @ApiModelProperty("居住面积")
    private String acreage;

    @ApiModelProperty("邮政编码")
    private String zipCode;

    @ApiModelProperty("房屋类型")
    private String houseType;

    @ApiModelProperty("厨房排风设施")
    private String exhaustFacility;

    @ApiModelProperty("厨房使用方式")
    private String kitchen;

    @ApiModelProperty("燃料")
    private String fuel;

    @ApiModelProperty("饮水")
    private String water;

    @ApiModelProperty("人均面积")
    private String perarea;

    @ApiModelProperty("职业")
    private String profession;

    @ApiModelProperty("工作单位")
    private String companyName;

    @ApiModelProperty("教育程度")
    private String degree;

    @ApiModelProperty("下三代")
    private List<UserFamilyDTO> children;

    @ApiModelProperty("上三代")
    private List<UserFamilyDTO> parent;

    @ApiModelProperty("所属健管师")
    private String healthManager;

}
