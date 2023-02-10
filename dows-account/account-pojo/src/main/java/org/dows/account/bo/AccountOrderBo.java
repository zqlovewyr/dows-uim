package org.dows.account.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountOrderBo {

    @ApiModelProperty(value = "账户ID")
    private Integer accountId;

    @ApiModelProperty(value = "消费金额sort")
    private Boolean consumption;


    @ApiModelProperty(value = "页码,默认为1")
    private Integer current = 1;
    @ApiModelProperty(value = "页大小,默认为10")
    private Integer size = 10;

    public void setCurrent(Integer current) {
        if (current == null || current <= 0){
            this.current = 1;
        }else{
            this.current = current;
        }
    }

    public void setSize(Integer size) {
        if (size == null || size <= 0){
            this.size = 10;
        }else{
            this.size = size;
        }
    }

}
