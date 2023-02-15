package org.dows.account.entity;

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
import org.dows.framework.crud.mybatis.CrudEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "AccountUserInfo对象", description = "账号-用户信息")
public class AccountUserInfo implements CrudEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("自增主键ID")
    private Long id;

    @ApiModelProperty("账号ID")
    private String accountId;

    @ApiModelProperty("租户ID")
    private String tenantId;
    @ApiModelProperty("名字")
    private String name;

    private Integer sex;

    private Date birthday;

    private Date createTime;

    private String clientNo;

    private String province;
    private String city;
    private String district;

    private String job;

    private String education;

    private String maritalStatus;

    private String nation;

    private Date entryTime;

    // 年代分布 60、70、80
    private String chronological;
    private String shengxiao;
    // 星座
    private String constellation;

    @JsonIgnore
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0")
    private Boolean deleted;
}
