package org.dows.account.biz.exception;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * 账号-角色 异常
 * @Author：wHuan
 * @Date：2022/11/28 15:18
 */
public class AccountRoleException extends BaseException {


    public AccountRoleException(String msg) {
        super(msg);
    }

    public AccountRoleException(Integer code, String msg) {
        super(code, msg);
    }

    public AccountRoleException(Throwable throwable) {
        super(throwable);
    }

    public AccountRoleException(StatusCode statusCode) {
        super(statusCode);
    }

    public AccountRoleException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public AccountRoleException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public AccountRoleException(StatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public AccountRoleException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }

    @Override
    public Object[] getArgs() {
        return super.getArgs();
    }
}
