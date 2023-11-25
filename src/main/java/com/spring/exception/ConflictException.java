package com.spring.exception;

import com.spring.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {

    public ConflictException(String message) {
        super(message);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}