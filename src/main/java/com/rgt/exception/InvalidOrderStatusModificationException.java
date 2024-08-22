package com.rgt.exception;

import com.rgt.constants.ExceptionMessage;

public class InvalidOrderStatusModificationException extends RuntimeException {
    public InvalidOrderStatusModificationException(String message) {
        super(message);
    }

}