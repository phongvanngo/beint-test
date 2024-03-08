package org.beint.beintappapi.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private final ErrorMessage errorMessage;

    public ValidationException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

}
