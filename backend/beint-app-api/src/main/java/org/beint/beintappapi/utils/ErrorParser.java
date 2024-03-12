package org.beint.beintappapi.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.beint.beintappapi.exceptions.ErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@RequiredArgsConstructor
@Component
public class ErrorParser {

    private final MessageSource messageSource;

    public ErrorMessage parse(String errorString, String[] args) {
        String code;
        String message;
        try {
            String[] errorDetails = errorString.split("\\|");
            code = errorDetails[0];
            message = MessageFormat.format(errorDetails[1], (Object) args);

        } catch (Exception e) {
            throw new RuntimeException("ErrorParser. ErrorString = " + errorString);
        }
        return new ErrorMessage() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    public ErrorMessage parse(String errorString) {
        return this.parse(errorString, null);
    }

    public ErrorMessage get(String messageKey, String... args) {
        return this.parse(messageSource.getMessage(messageKey, null, null), args);
    }

}