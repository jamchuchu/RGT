package com.rgt.exception;

import com.rgt.constants.ExceptionMessage;

public class AdditionalQuantityRequiredException extends RuntimeException {
    public AdditionalQuantityRequiredException() {
        super();
    }

    public AdditionalQuantityRequiredException(String message) {
        super(message);
    }
}