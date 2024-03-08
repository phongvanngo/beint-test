package org.beint.beintappapi.exceptions;

import org.beint.beintappapi.utils.AppErrorMessage;

public class HandleRequestException extends RuntimeException implements ErrorMessage {
    private final String code;
    private final String message;

    public HandleRequestException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public HandleRequestException(ErrorMessage appErrorMessage) {
        this(appErrorMessage.getCode(), appErrorMessage.getMessage());
    }

    public HandleRequestException(AppErrorMessage enumErrorCodes) {
        this(enumErrorCodes.getCode(), enumErrorCodes.getMessage());
    }

    public HandleRequestException() {
        this(AppErrorMessage.OTHER_ERROR.getCode(), AppErrorMessage.OTHER_ERROR.getMessage());
    }

    public HandleRequestException(String message) {
        this(AppErrorMessage.OTHER_ERROR.getCode(), message);
    }

    public HandleRequestException(ErrorMessage appErrorMessage, String... formatStringArgs) {
        this(appErrorMessage.getCode(), String.format(appErrorMessage.getMessage(), (Object) formatStringArgs));
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
