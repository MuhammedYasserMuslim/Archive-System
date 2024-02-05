package com.spring.exception;

import com.spring.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(String massage) {
        super(massage);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
