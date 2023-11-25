package com.spring.exception;

import com.spring.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class FileStorageException extends BaseException {

    public FileStorageException(String massage) {
        super(massage);
    }
    public FileStorageException(String massage, Throwable cause) {
        super(massage);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}