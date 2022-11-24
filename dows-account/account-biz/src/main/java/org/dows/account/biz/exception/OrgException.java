package org.dows.account.biz.exception;

import org.dows.framework.api.StatusCode;
import org.dows.framework.api.exceptions.BaseException;

/**
 * @author runsix
 */
public class OrgException extends BaseException {
    private static final long serialVersionUID = 6685937576436577750L;

    public OrgException(String msg) {
        super(msg);
    }

    public OrgException(Integer code, String msg) {
        super(code, msg);
    }

    public OrgException(Throwable throwable) {
        super(throwable);
    }

    public OrgException(StatusCode statusCode) {
        super(statusCode);
    }

    public OrgException(StatusCode statusCode, Exception exception) {
        super(statusCode, exception);
    }

    public OrgException(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

    public OrgException(StatusCode statusCode, Object[] args, String message) {
        super(statusCode, args, message);
    }

    public OrgException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(statusCode, args, message, cause);
    }
}
