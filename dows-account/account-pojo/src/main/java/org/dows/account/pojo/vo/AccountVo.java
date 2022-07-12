package org.dows.account.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountVo {
    @ApiModelProperty(value = "主键")
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;


    private String accountName;
    private String avatar;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "乐观锁, 默认: 0")
    private Integer ver;

    @ApiModelProperty(value = "时间戳/创建时间")
    private Date dt;
    private Integer status;
    private String phone;
    private String email;
    private String employeeNo;
    private String loginName;
    //    private List<RoleVo> roleList;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> roleIdList;
}
