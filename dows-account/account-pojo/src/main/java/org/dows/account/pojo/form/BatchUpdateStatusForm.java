package org.dows.account.pojo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateStatusForm {
    @ApiModelProperty("id数组")
    private List<Long> ids;
    @ApiModelProperty("状态")
    private EnumStatus status;
}
