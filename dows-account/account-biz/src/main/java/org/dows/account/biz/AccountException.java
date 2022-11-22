package org.dows.account.biz;

import org.dows.framework.api.exceptions.BaseException;

public class AccountException extends BaseException {
    public AccountException(String msg) {
        super(msg);
    }

    public AccountException(Integer code, String msg) {
        super(code, msg);
    }

    public AccountException(Throwable throwable) {
        super(throwable);
    }

    public AccountException(AccountStatusCode statusCode) {
        super(statusCode);
    }

    public AccountException(AccountStatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public AccountException(AccountStatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public AccountException(AccountStatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public AccountException(AccountStatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }
}
