package org.dows.user.exception;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * @author runsix
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = -2331222370818608486L;

    public UserException(String msg) {
        super(msg);
    }

    public UserException(Integer code, String msg) {
        super(code, msg);
    }

    public UserException(Throwable throwable) {
        super(throwable);
    }

    public UserException(StatusCode statusCode) {
        super(statusCode);
    }

    public UserException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public UserException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public UserException(StatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public UserException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }
}
