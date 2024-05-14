package com.moe.exception_handling.demo.exception;

public class ResourceAlreadyExistsException extends RuntimeException {


    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
