package org.dows.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dows.account.util.MyConstant;

import java.io.Serializable;

@Data
public class AccountIntegralVo implements Serializable {
    private static final long serialVersionUID = 9172367651136981993L;

    @ApiModelProperty(value = "积分类型")
    private String integralType;

    @ApiModelProperty(value = "积分描述")
    private String integralRemake;

    @ApiModelProperty(value = "积分量")
    private Integer integralCount;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = MyConstant.DEFAULT_DATE_TIME_FORMAT)
    private Data createTime;
























}
