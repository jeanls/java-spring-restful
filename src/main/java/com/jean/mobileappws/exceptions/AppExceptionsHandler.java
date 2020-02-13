package com.jean.mobileappws.exceptions;

import com.jean.mobileappws.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDescription)) {
            errorMessageDescription = ex.toString();
        }
        return new ResponseEntity<>(new ErrorMessage(new Date(), errorMessageDescription), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDescription)) {
            errorMessageDescription = ex.toString();
        }
        return new ResponseEntity<>(new ErrorMessage(new Date(), errorMessageDescription), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
