package com.baidu.dpop.ctp.common.exception;

public class BaseRuntimeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5942778788236202553L;
	private String message;
    private Throwable cause;

    public BaseRuntimeException(String message) {
        this.message = message;
    }

    public BaseRuntimeException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
