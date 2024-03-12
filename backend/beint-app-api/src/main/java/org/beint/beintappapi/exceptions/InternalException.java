package org.beint.beintappapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends BaseException {
    public InternalException(String errorCode) {
        super(errorCode);
    }

    public InternalException(ErrorMessage errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public InternalException(String errorCode, String message) {
        super(errorCode, message);
    }

    public InternalException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

}
