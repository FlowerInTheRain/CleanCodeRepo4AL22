package com.cleancode.bsimpl.exceptionsmanagement;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResponse extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value
            = { DBIMPLCommunicationException.class})
    protected ResponseEntity<Object> handleConflict(
            DBIMPLCommunicationException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getResponse(),
                new HttpHeaders(), ex.getResponse().responseStatus(), request);
    }
}