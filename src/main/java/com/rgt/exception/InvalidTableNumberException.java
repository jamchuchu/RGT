package com.rgt.exception;

import com.rgt.constants.ExceptionMessage;

public class InvalidTableNumberException extends RuntimeException {
    public InvalidTableNumberException(String message) {
        super(message);
    }

}