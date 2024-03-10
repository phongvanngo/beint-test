package org.beint.beintappapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends BaseException {

    public ValidationException(ErrorMessage errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public ValidationException(String errorCode) {
        super(errorCode);
    }

    public ValidationException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ValidationException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(ErrorMessage msgSts, String value) {
        super(msgSts, value);
    }

    public ValidationException(List<String> listMessage) {
        super(listMessage);
    }
}
