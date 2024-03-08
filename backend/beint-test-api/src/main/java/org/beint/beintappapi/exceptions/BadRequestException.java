package org.beint.beintappapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException(ErrorMessage errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public BadRequestException(String errorCode) {
        super(errorCode);
    }

    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }

    public BadRequestException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(ErrorMessage msgSts, String value) {
        super(msgSts, value);
    }

    public BadRequestException(List<String> listMessage) {
        super(listMessage);
    }
}
