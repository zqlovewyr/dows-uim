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

    @ApiModelProperty("时间戳")
    private Date dt;

    @ApiModelProperty("下三代")
    private List<UserFamilyDTO> children;

    @ApiModelProperty("上三代")
    private List<UserFamilyDTO> parent;

}
