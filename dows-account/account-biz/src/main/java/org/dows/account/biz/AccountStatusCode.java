package org.dows.account.biz;

import lombok.Getter;
import org.dows.framework.api.StatusCode;

public enum AccountStatusCode implements StatusCode {
    ACCOUNT_EXESIT_EXCPETION(400000, "账号已存在"),

    ACCOUNT_NOT_EXCPETION1(400000, "账号不存在1"),
    ACCOUNT_NOT_EXCPETION2(400000, "账号不存在2");


    @Getter
    int code;

    @Getter
    String descr;

    AccountStatusCode(int code, String descr) {
        this.code = code;
        this.descr = descr;

    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getDescr() {
        return null;
    }
}
