package org.beint.beintappapi.exceptions;



import lombok.Getter;

import java.util.List;

public class BaseException extends RuntimeException {
    @Getter
    private String errorCode;
    private String message;
    @Getter
    private List<String> listMessage;

    protected BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    protected BaseException(List<String> listMessage) {
        this.listMessage = listMessage;
    }

    protected BaseException(ErrorMessage errorMessage) {
        this.errorCode = errorMessage.getCode();
        this.message = errorMessage.getMessage();
    }

    protected BaseException(ErrorMessage errorMessage, String value) {
        this.errorCode = errorMessage.getCode();
        this.message = value;
    }

    protected BaseException(String errorCode) {
        this.errorCode = errorCode;
    }

    protected BaseException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    protected BaseException(Throwable cause) {
        super(cause);
        this.errorCode = "INTERNAL_SERVER_ERROR";
        this.message = cause.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
