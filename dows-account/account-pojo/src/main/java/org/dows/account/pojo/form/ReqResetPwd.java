package org.dows.account.pojo.form;

import lombok.Data;

@Data
public class ReqResetPwd {
    private Long userId;
    private Boolean send;
}
