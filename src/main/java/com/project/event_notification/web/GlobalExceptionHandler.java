package com.project.event_notification.web;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ServerErrorDto> handleNotValidArgumentException(
            IllegalArgumentException exception
    ) {
        return null;
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ServerErrorDto> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        return null;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            Exception exception
    ) {
        return null;
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            IllegalArgumentException exception
    ) {
        return null;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            MethodArgumentNotValidException exception
    ) {
        return null;
    }
}
