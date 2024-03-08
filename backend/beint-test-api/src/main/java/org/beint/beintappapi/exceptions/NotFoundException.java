


package org.beint.beintappapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {
    public NotFoundException(String errorCode) {
        super(errorCode);
    }

    public NotFoundException(ErrorMessage errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public NotFoundException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}