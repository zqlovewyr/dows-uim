package org.dows.account.biz;

import org.dows.rbac.biz.RbacException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author runsix
 */
@RestControllerAdvice
public class AccountInstanceExceptionHandler {
    @ExceptionHandler(RbacException.class)
    public void handleRbacException(RbacException rbacException) {
        System.out.println("1");
    }
}
