package com.spring.exception;

import com.spring.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class RecordNotFountException extends BaseException {

    public RecordNotFountException(String massage) {
        super(massage);
    }


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
