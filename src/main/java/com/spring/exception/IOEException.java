package com.spring.exception;

import com.spring.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class IOEException extends BaseException {

    public IOEException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return  HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
