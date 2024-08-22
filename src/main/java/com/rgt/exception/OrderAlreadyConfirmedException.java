package com.rgt.exception;

import com.rgt.constants.ExceptionMessage;

public class OrderAlreadyConfirmedException extends RuntimeException {
    public OrderAlreadyConfirmedException(String message) {
        super(message);
    }

}