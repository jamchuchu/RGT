package com.rgt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdditionalQuantityRequiredException.class)
    public ResponseEntity<String> handleAdditionalQuantityRequiredException(AdditionalQuantityRequiredException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleItemNotFoundException(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(InvalidOrderStatusModificationException.class)
    public ResponseEntity<String> handleInvalidOrderStatusModificationException(InvalidOrderStatusModificationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidTableNumberException.class)
    public ResponseEntity<String> handleInvalidTableNumberException(InvalidTableNumberException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(OrderAlreadyConfirmedException.class)
    public ResponseEntity<String> handleOrderAlreadyConfirmedException(OrderAlreadyConfirmedException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    // 처리되지 않은 예외를 위한 일반적인 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 내부 오류가 발생했습니다: " + ex.getMessage());
    }
}