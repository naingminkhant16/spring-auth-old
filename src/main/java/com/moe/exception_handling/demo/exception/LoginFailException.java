package com.moe.exception_handling.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginFailException extends RuntimeException {
    public LoginFailException(String message) {
        super(message);
    }
}
