package org.dows.rbac.api.exception;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * @author runsix
 */

public class RbacException extends BaseException {
    public RbacException(String msg) {
        super(msg);
    }

    public RbacException(Integer code, String msg) {
        super(code, msg);
    }

    public RbacException(Throwable throwable) {
        super(throwable);
    }

    public RbacException(StatusCode statusCode) {
        super(statusCode);
    }

    public RbacException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public RbacException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public RbacException(StatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public RbacException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }
}
