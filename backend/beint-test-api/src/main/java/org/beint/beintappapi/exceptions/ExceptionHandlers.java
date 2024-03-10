package org.beint.beintappapi.exceptions;

import org.beint.beintappapi.dto.response.BaseResponse;
import org.beint.beintappapi.utils.AppErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;


@ControllerAdvice
public class ExceptionHandlers {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequest(BadRequestException exception) {
        return createErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationError(ValidationException exception) {
        return createErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFound(NotFoundException exception) {
        return createErrorResponse(exception, HttpStatus.NOT_FOUND, exception.getErrorCode(), exception.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(Exception exception) {
        List<ObjectError> allErrors = ((MethodArgumentNotValidException) exception).getBindingResult()
                .getAllErrors();
        String validationErrors = allErrors
                .stream()
                .map(e -> ((FieldError) e).getField() + ":" + e.getDefaultMessage())
                .toList()
                .toString();
        return createErrorResponse(exception, HttpStatus.BAD_REQUEST, AppErrorMessage.VALID_ERROR.getCode(), validationErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return createErrorResponse(exception, HttpStatus.BAD_REQUEST, AppErrorMessage.REQUEST_BODY_ERROR.getCode(), AppErrorMessage.REQUEST_BODY_ERROR.getMessage());

    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Throwable throwable) {
        logger.error(throwable.getMessage());
        return createErrorResponse(throwable, HttpStatus.INTERNAL_SERVER_ERROR, AppErrorMessage.INTERNAL_SERVER_ERROR.getCode(), throwable.getMessage());
    }

    private ResponseEntity<Object> createErrorResponse(Throwable throwable, HttpStatus status, String errorCode, String errorDesc) {
        logger.warn(throwable.getMessage());
        logger.error("#exception error: {}", errorDesc);
        BaseResponse dataResponse = new BaseResponse();
        dataResponse.setCode(errorCode);
        dataResponse.setMess(errorDesc);
        return ResponseEntity.status(status.value()).body(dataResponse);
    }

}

