package com.acloudglobal.ctsbewx.exception;

/**
 * 应用异常
 *
 * @author yupl@acloudchina.com
 * @date 2019-12-09 3:58 下午
 * @since V2.0.0
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 5162710183389028792L;

    private String message;

    private AppException() {
    }

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
