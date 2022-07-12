package org.dows.account.pojo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupUpdate {
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String groupName;
    private String descr;
    @ApiModelProperty("租户ID")
    private Long tenantId;
}
