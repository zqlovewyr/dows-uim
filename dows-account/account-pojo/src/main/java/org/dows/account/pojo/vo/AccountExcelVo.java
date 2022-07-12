package org.dows.account.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dows.framework.api.EnumStatus;
import lombok.Data;

@Data
public class AccountExcelVo {

    /* @ApiModelProperty(value = "账户ID")
     @ExcelProperty("账户ID")
     private Long id;*/
    @ExcelProperty("账户名")
    private String accountName;
    @ExcelIgnore
    private Integer status;
    @ExcelProperty("状态")
    private String statusName;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("员工编码")
    private String employeeNo;
    @ExcelProperty("登陆名")
    private String loginName;

    public String getStatusName() {
        if (EnumStatus.enable.getValue().equals(this.status)) {
            return EnumStatus.enable.getDesc();
        }
        if (EnumStatus.disable.getValue().equals(this.status)) {
            return EnumStatus.disable.getDesc();
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
