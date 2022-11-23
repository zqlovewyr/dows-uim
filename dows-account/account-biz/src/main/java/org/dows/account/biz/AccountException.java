package org.dows.account.biz;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * @author runsix
 */
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

    public AccountException(StatusCode statusCode) {
        super(statusCode);
    }

    public AccountException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public AccountException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public AccountException(StatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public AccountException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }
}
