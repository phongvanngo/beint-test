package org.beint.beintappapi.utils;

import org.beint.beintappapi.exceptions.ErrorMessage;

public enum AppErrorMessage implements ErrorMessage {
    SUCCESS("00", "SUCCESS"),
    INTERNAL_SERVER_ERROR("100", "INTERNAL_SERVER_ERROR"),
    REQUEST_BODY_ERROR("101", "REQUEST_BODY_ERROR"),
    VALID_ERROR("103", "VALID_ERROR"),
    OTHER_ERROR("999", "OTHER_ERRORS");

    private final String code;
    private final String message;

    AppErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
