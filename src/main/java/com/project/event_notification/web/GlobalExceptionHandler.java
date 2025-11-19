package com.project.event_notification.web;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ServerErrorDto> handleNotValidArgumentException(
            IllegalArgumentException exception
    ) {
        log.error("Got bad credential exception", exception);
        var errorDTO = new ServerErrorDto("bad credential",
                exception.getMessage(),
                OffsetDateTime.now());

        return ResponseEntity.status(401).body(errorDTO);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ServerErrorDto> handleEntityNotFoundException(
            EntityNotFoundException exception
    ) {
        log.info("Not valid result: Entity no found");
        var errorDTO = new ServerErrorDto(
                "Entity not found",
                exception.getMessage(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            Exception exception
    ) {
        log.error("Got exception", exception);
        var errorDTO = new ServerErrorDto("Server exception",
                exception.getMessage(),
                OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            IllegalArgumentException exception
    ) {
        log.error("Not valid result: Bad request");
        var errorDTO = new ServerErrorDto("Illegal argument",
                exception.getMessage(),
                OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ServerErrorDto> handleGlobalServerException(
            MethodArgumentNotValidException exception
    ) {
        log.info("Not valid result");
        String detailMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(it -> it.getField() + " " + it.getDefaultMessage())
                .collect(Collectors.joining(", "));

        var errorDTO = new ServerErrorDto(
                "Not valid result",
                detailMessage,
                OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

}
